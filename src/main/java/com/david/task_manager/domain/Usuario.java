package com.david.task_manager.domain;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    String password;
    @JsonIgnore
    String email;

    @OneToMany(mappedBy = "responsible", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Task> tasks;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    RoleEnum role;

}
