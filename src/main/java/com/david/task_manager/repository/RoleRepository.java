package com.david.task_manager.repository;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);

    @Query(value = "SELECT role_id FROM usuario_roles r WHERE r.usuario_id = :usuarioId", nativeQuery = true)
    Set<Role> findRolesByUserId(@Param("usuarioId") Long usuarioId);
}
