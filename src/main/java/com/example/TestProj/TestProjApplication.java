package com.example.TestProj;

import com.example.TestProj.entity.Task;

import com.example.TestProj.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class TestProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner runner(TaskRepository taskRepository) {
		return args -> {
			// Clear existing data and reset sequence
			taskRepository.deleteAll();

			// Flush to ensure delete completes
			taskRepository.flush();

			// Create new tasks with fresh state
			List<Task> tasks = List.of(
					new Task(null, "task1", false),  // Let DB generate ID
					new Task(null, "task2", false),
					new Task(null, "task3", false),
					new Task(null, "task4", false)
			);

			taskRepository.saveAll(tasks);
		};
	}
}
