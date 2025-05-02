package com.example.TestProj.service;

import com.example.TestProj.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    void addTask(Task task);
    void completeTask(Task task);
}
