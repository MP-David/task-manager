package com.david.task_manager.controller;

import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import com.david.task_manager.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.david.task_manager.util.Builders.TaskCreator.createTaskDTOList;
import static com.david.task_manager.util.Builders.TaskCreator.createValidTaskDTO;
import static com.david.task_manager.util.Request.TaskPostCreator.createMockTaskPostRequestBody;

@ExtendWith(SpringExtension.class)
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskServiceMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(taskServiceMock.findAllWithLimitedUserInfo())
                .thenReturn(createTaskDTOList());

        BDDMockito.when(taskServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidTaskDTO());

        BDDMockito.when(taskServiceMock.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(createTaskDTOList());

        BDDMockito.when(taskServiceMock.save(ArgumentMatchers.any(TaskPostRequestBody.class)))
                .thenReturn(createValidTaskDTO());

        BDDMockito.doNothing().when(taskServiceMock).update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(TaskPutRequestBody.class));

        BDDMockito.doNothing().when(taskServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("Save Task whenSuccessful")
    void save() {
        ResponseEntity<TaskDTO> response = taskController.save(createMockTaskPostRequestBody());

        Assertions.assertThat(response)
                .isNotNull()
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(response.getBody())
                .isNotNull()
                .extracting(TaskDTO::getTitle)
                .isEqualTo(createValidTaskDTO().getTitle());

        Assertions.assertThat(response.getBody()).isEqualTo(createValidTaskDTO());

    }

    @Test
    @DisplayName("findAll Return list of TaskDTO whenSuccessful")
    void findAll() {
        ResponseEntity<List<TaskDTO>> response = taskController.findAll();

        Assertions.assertThat(response).isNotNull()
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(response.getBody())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(response.getBody().get(0))
                .isEqualTo(createValidTaskDTO());

        Assertions.assertThat(response.getBody().get(0)).isEqualTo(createValidTaskDTO());
    }

    @Test
    @DisplayName("findByTitle Return TaskDTO whenSuccessful")
    void findByTitle() {
        ResponseEntity<List<TaskDTO>> byTitle = taskController.findByTitle(createMockTaskPostRequestBody().title());

        Assertions.assertThat(byTitle).isNotNull()
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(byTitle.getBody())
                        .hasSize(1);

        Assertions.assertThat(byTitle.getBody().get(0).getTitle())
                .isEqualTo(createValidTaskDTO().getTitle());

        Assertions.assertThat(byTitle.getBody().get(0)).isEqualTo(createValidTaskDTO());
    }

    @Test
    @DisplayName("findById Return TaskDTO whenSuccessful")
    void findById() {
        ResponseEntity<TaskDTO> response = taskController.findById(1L);

        Assertions.assertThat(response)
                .isNotNull().extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(response.getBody().getId()).isEqualTo(createValidTaskDTO().getId());

        Assertions.assertThat(response.getBody()).isEqualTo(createValidTaskDTO());

    }

    @Test
    @DisplayName("update Task whenSuccessful")
    void update() {
        ResponseEntity<Void> update = taskController.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(TaskPutRequestBody.class));

        Assertions.assertThatCode(() -> taskController.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(TaskPutRequestBody.class)))
                .doesNotThrowAnyException();

        Assertions.assertThat(update).isNotNull();

        Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete Task whenSuccessful")
    void delete() {
        ResponseEntity<Void> delete = taskController.delete(1L);

        Assertions.assertThatCode(() -> taskController.delete(ArgumentMatchers.anyLong()))
                .doesNotThrowAnyException();

        Assertions.assertThat(delete).isNotNull();

        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}