package com.david.task_manager.repository;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.domain.Role;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findRoleById(Long id);

    Optional<Role> findByName(RoleEnum name);

    Set<Role> findAllByNameIn(Set<RoleEnum> names);

    @Query(value = "SELECT role_id FROM usuario_roles r WHERE r.usuario_id = :usuarioId", nativeQuery = true)
    Set<Role> findRolesByUserId(@Param("usuarioId") Long usuarioId);


    @Named("mapRoles")
    default Set<Role> mapRoles(Set<RoleEnum> roleEnums) {
        if (roleEnums == null || roleEnums.isEmpty()) {
            return new HashSet<>();
        }
        Set<Role> roles = findAllByNameIn(roleEnums);
        if (roles.size() != roleEnums.size()) {
            throw new IllegalArgumentException("Some roles do not exist");
        }
        return roles;
    }


//    default Set<Role> mapRoles(Set<RoleEnum> roleEnums) {
//        if (roleEnums == null || roleEnums.isEmpty()) {
//            return new HashSet<>();
//        }
//        Set<Role> roles = findAllByNameIn(roleEnums);
//        if (roles.size() != roleEnums.size()) {
//            throw new IllegalArgumentException("Some roles do not exist");
//        }
//        return roles;
//    }

//    Set<Role> roles = usuarioPostRequestBody.getRoles().stream()
//            .map(roleEnum -> {
//                Role role = roleRepository.findByName(roleEnum);
//                if(role == null) {
//                    throw new BadRequest("Role Inexistete");
//                }
//                return role;
//            })
//            .collect(Collectors.toSet());
}
