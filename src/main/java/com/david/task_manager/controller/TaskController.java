package com.david.task_manager.controller;

import com.david.task_manager.domain.Task;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import com.david.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Task> save(@RequestBody @Valid TaskPostRequestBody taskPostRequestBody) {
        return new ResponseEntity<>(taskService.save(taskPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> findAll(Pageable pageable) {
            return ResponseEntity.ok(taskService.findAll(pageable));
    }

    @GetMapping(path = "find")
    public ResponseEntity<List<Task>> findByTitle(@RequestParam(required = true) String title) {
        return ResponseEntity.ok(taskService.findByTitle(title));
    }

    @GetMapping(path = "find/{id}")
    public ResponseEntity<Task> findById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.findByIdOrElseThrowBadRequest(id));
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
