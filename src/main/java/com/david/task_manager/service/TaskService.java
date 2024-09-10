package com.david.task_manager.service;

import com.david.task_manager.domain.Task;
import com.david.task_manager.exception.BadRequest;
import com.david.task_manager.mapper.TaskMapper;
import com.david.task_manager.repository.TaskRepository;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import com.david.task_manager.utils.UtilsNullPointers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Page<Task> findAll(Pageable page) {
        return taskRepository.findAll(page);
    }

    public Task findByIdOrElseThrowBadRequest(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Id não encontrado"));
    }

    public List<Task> findByTitle(String title) {
        return taskRepository.findByTitle(title);
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
    public Task save(TaskPostRequestBody taskPostRequestBody) {
        validateTaskStage(taskPostRequestBody.getStage().getCodigo());
        return taskRepository.save(taskMapper.toTask(taskPostRequestBody));
    }

    public void validateTaskStage(int stage) {
        if (stage < 0 || stage > 2) {
            throw new IllegalArgumentException("Código de status inválido: " + stage);
        }
    }

}

