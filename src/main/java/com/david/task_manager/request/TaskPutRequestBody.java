package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskPutRequestBody (String title,
                                 String description,
                                 StageEnum stage,
                                 LocalDateTime endDate,
                                 PriorityEnum priorityEnum,
                                 Long score,
                                 Long responsibleId
) {}
