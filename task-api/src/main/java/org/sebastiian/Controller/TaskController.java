package org.sebastiian.Controller;


import org.sebastiian.Entity.Task;
import org.sebastiian.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return service.createTask(task.getTaskName(), task.getTaskDescription());
    }

    @GetMapping("/{id}")
    public Task findTaskById(@PathVariable int id) {
        return service.findTaskById(id);
    }

    @GetMapping("/name/{taskName}")
    public List<Task> findTaskByTaskName(@PathVariable String taskName) {
        return service.findTaskByName(taskName);
    }

    @GetMapping
    public List<Task> findAllTasks() {
        return service.findAllTasks();
    }

    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable int id,
                           @RequestParam String taskName,
                           @RequestParam String taskDescription) {
        return service.updateTask(id, taskName, taskDescription);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) {
        service.deleteTask(id);
    }

}
