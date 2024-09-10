package com.david.task_manager.domain;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    LocalDate initDate;
    LocalDate endDate;
    Long score;

    @ManyToOne
    Usuario responsible;

    @Enumerated(EnumType.STRING)
    PriorityEnum priority;

    @Enumerated(EnumType.STRING)
    StageEnum stage;

}
