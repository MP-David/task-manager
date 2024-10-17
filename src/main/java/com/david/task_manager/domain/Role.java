package com.david.task_manager.domain;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleEnum name;

}
