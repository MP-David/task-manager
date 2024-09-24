package com.david.task_manager.repository;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}
