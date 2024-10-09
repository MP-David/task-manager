package com.david.task_manager.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionsDetails {
        private String title;
        private int status;
        private String details;
        private String developMessage;
        private LocalDateTime timestamp;
}
