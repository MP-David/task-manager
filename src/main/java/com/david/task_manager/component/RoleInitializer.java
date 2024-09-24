package com.david.task_manager.component;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.domain.Role;
import com.david.task_manager.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            roleRepository.findByName(roleEnum).orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName(roleEnum);
                return roleRepository.save(newRole);
            });
        }
    }
}
