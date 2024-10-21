package com.david.task_manager.service;

import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.dto.UsuarioDTO;
import com.david.task_manager.mapper.TaskMapper;
import com.david.task_manager.mapper.UsuarioMapper;
import com.david.task_manager.repository.TaskRepository;

import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;
    @Spy
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    private Task task;
    private List<Task> ListOfTask = new ArrayList<>();

    //Injeção do mapper de Usuario no mapper de Task
    @BeforeEach
    public void init() {
        UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
        ReflectionTestUtils.setField(taskMapper, "usuarioMapper", usuarioMapper);
    }

    @BeforeEach
    void setUp() {

        this.task = Instancio.create(Task.class);
        this.ListOfTask = Instancio.ofList(Task.class).size(10).create();

        when(taskRepository.findAll())
                .thenReturn(ListOfTask);

        when(taskRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(task));
    }

    @Test
    void findAll() {

        List<TaskDTO> tasks = taskService.findAll();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty()
                .hasSize(10);

        Assertions.assertThat(tasks.get(0))
                .isNotNull()
                .isEqualTo(taskMapper.toTaskDTO(ListOfTask.get(0)));
    }

    @Test
    void findById() {

        TaskDTO taskDTO = taskService.findById(task.getId());

        Assertions.assertThat(taskDTO)
                .isNotNull()
                .extracting(TaskDTO::getId)
                .isEqualTo(task.getId());
    }

    @Test
    void findByIdOrElseThrowBadRequest() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void findValidTasks() {
    }

    @Test
    void validateTaskStage() {
    }
}