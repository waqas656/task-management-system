package com.api.taskmanagementsystem.controllers;

import com.api.taskmanagementsystem.models.entities.Task;
import com.api.taskmanagementsystem.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        Task task1 = taskService.addTask(task);
        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable String id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTaskById(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable String id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/id/{id}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable String id) {
        Task task = taskService.markTaskAsComplete(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Task> getTaskByTitle(@PathVariable String title) {
        Task task = taskService.getTaskByTitle(title);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/due-soon")
    public ResponseEntity<List<Task>> getDueTasksInNextDays() {
        List<Task> dueTasks = taskService.getDueTasks();
        return ResponseEntity.ok(dueTasks);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> completedTasks = taskService.getCompletedTasks();
        return ResponseEntity.ok(completedTasks);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String query) {
        List<Task> foundTasks = taskService.searchTasks(query);
        return ResponseEntity.ok(foundTasks);
    }

}
