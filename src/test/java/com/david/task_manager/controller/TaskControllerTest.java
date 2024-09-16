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

import static com.david.task_manager.util.TaskCreator.createTaskDTOList;
import static com.david.task_manager.util.TaskCreator.createValidTaskDTO;
import static com.david.task_manager.util.TaskPostCreator.createMockTaskPostRequestBody;

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
    @DisplayName("Save Task whenSucessfull")
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

    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}