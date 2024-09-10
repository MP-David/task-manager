package com.david.task_manager.mapper;

import com.david.task_manager.domain.Task;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper{

    @Mapping(target = "initDate", expression = "java(java.time.LocalDate.now())")
    Task toTask(TaskPostRequestBody taskPostRequestBody);

    Task toTask(TaskPutRequestBody taskPutRequestBody);

}
