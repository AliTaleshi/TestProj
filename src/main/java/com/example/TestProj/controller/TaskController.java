package com.example.TestProj.controller;

import com.example.TestProj.entity.Task;
import com.example.TestProj.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @PutMapping("/{id}/complete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
    }
}
