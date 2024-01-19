package com.api.taskmanagementsystem.services;

import com.api.taskmanagementsystem.models.entities.Task;
import com.api.taskmanagementsystem.repos.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TasksRepository tasksRepository;

    public Task addTask(Task task) {
        return tasksRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Task getTaskById(String id) {
        Optional<Task> optionalTask = tasksRepository.findById(id);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }

        return null;
    }

    public Task getTaskByTitle(String title) {
        return tasksRepository.findTaskByTitle(title);
    }

    public Task updateTaskById(String id, Task update) {
        Optional<Task> task = tasksRepository.findById(id);

        if (task.isPresent()) {
            Task taskToSave = task.get();
            taskToSave.setTitle(update.getTitle());
            taskToSave.setDescription(update.getDescription());
            taskToSave.setDueDate(update.getDueDate());

            return tasksRepository.save(taskToSave);
        }

        return null;
    }

    public Task markTaskAsComplete(String id) {
        Optional<Task> optionalTask = tasksRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);

            return tasksRepository.save(task);
        }

        return null;
    }

    public void deleteTaskById(String id) {
        Optional<Task> optionalTask = tasksRepository.findById(id);

        if (optionalTask.isPresent()) {
            tasksRepository.delete(optionalTask.get());
        }
    }

    public List<Task> getDueTasks() {
        //calculate the date that is week away
        int date = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        String todaysFullDate = date + "-" + month + "-" +year;
        String dueDateFull = (date + 7) + "-" + month + "-" +year;

        return tasksRepository.findAllDueDateTasks(todaysFullDate, dueDateFull);
    }

    public List<Task> getCompletedTasks() {
        return tasksRepository.getCompletedTasks();
    }

    public List<Task> searchTasks(String query) {
        return tasksRepository.searchTasks(query);
    }
}
