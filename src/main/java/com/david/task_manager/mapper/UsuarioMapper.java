package com.david.task_manager.mapper;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Role;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.UsuarioLimitadoDTO;
import com.david.task_manager.repository.RoleRepository;
import com.david.task_manager.request.UsuarioPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    protected RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Mapping(target = "roles", expression = "java(mapRoles(usuarioPostRequestBody.getRoles()))")
    public abstract Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

    protected StageEnum mapStringToStageEnum(String stage) {
        if (stage == null) {
            return null;
        }
        return StageEnum.forValue(stage.toUpperCase());
    }

    protected UsuarioLimitadoDTO toUsuarioLimitadoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioLimitadoDTO dto = new UsuarioLimitadoDTO();
        dto.setId(usuario.getId());
        dto.setName(usuario.getName());
        dto.setUsername(usuario.getUsername());
        return dto;
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
