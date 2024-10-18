package com.david.task_manager.domain;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Schema(hidden = true)
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
    @JoinColumn(name = "responsible_id")
    private Usuario responsible;

    @Enumerated(EnumType.STRING)
    PriorityEnum priority;

    @Enumerated(EnumType.STRING)
    StageEnum stage;

}
