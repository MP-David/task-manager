package com.david.task_manager.mapper;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.domain.Role;
import com.david.task_manager.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    protected RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    protected Set<Role> mapRoles(Set<RoleEnum> roleEnums) {
        if (roleEnums == null) {
            return null;
        }
        return roleEnums.stream()
                .map(roleEnum -> roleRepository.findByName(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum)))
                .collect(Collectors.toSet());
    }


}
