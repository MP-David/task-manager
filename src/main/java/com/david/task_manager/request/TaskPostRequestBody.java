package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskPostRequestBody(@NotEmpty String title,
                                  @NotEmpty String description,
                                  @NotNull String stage,
                                  LocalDate initDate,
                                  LocalDate endDate,
                                  PriorityEnum priority,
                                  Long score,
                                  Long responsibleId
) {}
