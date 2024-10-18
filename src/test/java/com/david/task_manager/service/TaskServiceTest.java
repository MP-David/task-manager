package com.david.task_manager.service;

import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.dto.UsuarioDTO;
import com.david.task_manager.mapper.TaskMapper;
import com.david.task_manager.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.mapping.Any;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.david.task_manager.util.Builders.TaskCreator.createTaskDTOList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;


    private List<Task> ListOfTask = new ArrayList<>();
    private Task task;

    @BeforeEach
    void setUp() {

        this.task = Instancio.create(Task.class);
        this.ListOfTask = Instancio.ofList(Task.class).size(10).create();

        BDDMockito.when(taskRepository.findAll())
                .thenReturn(ListOfTask);

        BDDMockito.when(taskMapper.toTaskDTOList(ArgumentMatchers.any()))
                .thenReturn(toTaskDTOList(ListOfTask));

    }

//    TODO @ParameterizedTest
//    TODO @MethodSource("createList")
    @Test
    void findAll() {

        List<TaskDTO> tasks = taskService.findAll();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty()
                .hasSize(10);

        Assertions.assertThat(tasks.get(0))
                .isNotNull()
                .isEqualTo(toTaskDTO(ListOfTask.get(0)));
    }

    @Test
    void findById() {
//
//        TaskDTO taskDTO = taskService.findById(task.getId());
//
//        Assertions.assertThat(taskDTO)
//                .isNotNull()
//                .extracting(TaskDTO::getId)
//                .isEqualTo(task.getId());
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

//    private static Stream<Arguments> createList() {
//        return Stream.of(Arguments.of(Instancio.ofList(Task.class).size(10).create()));
//
//    }

    protected UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(usuario.getId(), usuario.getName(), usuario.getUsername());
    }

    public TaskDTO toTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO.TaskDTOBuilder taskDTO = TaskDTO.builder();

        taskDTO.responsible( toUsuarioDTO( task.getResponsible() ) );
        taskDTO.id( task.getId() );
        taskDTO.title( task.getTitle() );
        taskDTO.description( task.getDescription() );
        taskDTO.initDate( task.getInitDate() );
        taskDTO.endDate( task.getEndDate() );
        taskDTO.score( task.getScore() );
        taskDTO.priority( task.getPriority() );
        taskDTO.stage( task.getStage() );

        return taskDTO.build();
    }

    public List<TaskDTO> toTaskDTOList(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( toTaskDTO( task ) );
        }

        return list;
    }
}