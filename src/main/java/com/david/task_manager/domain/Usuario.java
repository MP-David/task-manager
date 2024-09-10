package com.david.task_manager.domain;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String username;
    String password;
    String email;

    @OneToMany(mappedBy = "responsible", fetch = FetchType.EAGER)
    private Set<Task> tasks;

    @Enumerated(EnumType.STRING)
    RoleEnum role;

}
