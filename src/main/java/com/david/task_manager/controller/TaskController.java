package com.david.task_manager.controller;

import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import com.david.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping(path = "task")
@RestController
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> save(@RequestBody @Valid TaskPostRequestBody taskPostRequestBody) {
        return new ResponseEntity<>(taskService.save(taskPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping(path = "page")
    public ResponseEntity<Page<TaskDTO>> findAll(@ParameterObject Pageable pageable) {
            return ResponseEntity.ok(taskService.findAll(pageable));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping(path = "find")
    public ResponseEntity<List<TaskDTO>> findByTitle(@RequestParam(required = true) String title) {
        return ResponseEntity.ok(taskService.findByTitle(title));
    }

    @GetMapping(path = "find/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody TaskPutRequestBody taskPutRequestBody) {
        taskService.update(id, taskPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
