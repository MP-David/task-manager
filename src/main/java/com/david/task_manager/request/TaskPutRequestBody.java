package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TaskPutRequestBody {

    String title;
    String description;
    StageEnum stage;
    LocalDateTime endDate;
    PriorityEnum priorityEnum;
    Long score;

}
