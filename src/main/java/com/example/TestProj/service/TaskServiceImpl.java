package com.example.TestProj.service;

import com.example.TestProj.entity.Task;
import com.example.TestProj.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;


    // TODO: I should probably use DTOs
    @Override
    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks found");
        }
        return tasks;
    }

    @Override
    public void addTask(Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task id is null");
        }

        taskRepository.save(task);
        log.info("Task added with id: " + task.getId());
    }

    @Override
    public void completeTask(Long taskId) {
        if (taskRepository.findById(taskId).isEmpty()) {
            throw new IllegalArgumentException("No Task found with id: " + taskId);
        }

        Task task = taskRepository.findById(taskId).get();
        task.setCompleted(true);
        taskRepository.save(task);
        log.info("Task completed with id: " + task.getId());
    }
}
