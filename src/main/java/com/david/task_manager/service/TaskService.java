package com.david.task_manager.service;

import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.exception.TaskNotFoundException;
import com.david.task_manager.mapper.TaskMapper;
import com.david.task_manager.repository.TaskRepository;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import com.david.task_manager.utils.UtilsNullPointers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UsuarioService usuarioService;

    public List<TaskDTO> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toTaskDTOList(tasks);
    }

    public Page<TaskDTO> findAll(Pageable page) {
        Page<Task> taskPage = taskRepository.findAll(page);
        return taskMapper.toTaskDTOPage(taskPage);
    }

    public TaskDTO findById(long id) {
        Task task = findByIdOrElseThrowBadRequest(id);
        return taskMapper.toTaskDTO(task);
    }

    public Task findByIdOrElseThrowBadRequest(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Id não encontrado"));
    }

    public List<TaskDTO> findByTitle(String title) {
        List<Task> byTitle = taskRepository.findByTitle(title);
        return taskMapper.toTaskDTOList(byTitle);
    }

    @Transactional
    public void delete(long id) {
        findByIdOrElseThrowBadRequest(id);
        taskRepository.deleteById(id);
    }

    @Transactional
    public void update(long id, TaskPutRequestBody putRequestBody) {
        Task task = findByIdOrElseThrowBadRequest(id);
        Task updater = taskMapper.toTask(putRequestBody);
        BeanUtils.copyProperties(updater, task, UtilsNullPointers.getNullPropertyNames(updater));
        taskRepository.save(task);
    }

    @Transactional
    public TaskDTO save(TaskPostRequestBody taskPostRequestBody) {
        Usuario usuario = usuarioService.findById(taskPostRequestBody.responsibleId());
        validateTaskStage(taskPostRequestBody.stage());

        Task task = taskMapper.toTask(taskPostRequestBody);
        task.setResponsible(usuario);
        taskRepository.save(task);

        return taskMapper.toTaskDTO(task);
    }

    @Transactional
    public void saveAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }

    public List<Task> findValidTasks(StageEnum stage) {
        return taskRepository.findValidTasks(stage);
    }

    public void validateTaskStage(String stage) {
        try {
            StageEnum.valueOf(stage);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Código de status inválido: " + stage +
                    ". Deve estar entre " + StageEnum.validValues());
        }
    }

}

