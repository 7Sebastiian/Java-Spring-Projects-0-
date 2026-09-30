package org.sebastiian.Service;

import org.sebastiian.Entity.Task;
import org.sebastiian.Repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository){
        this.repository = repository;
    }

    @Transactional
    public Task createTask(String taskName, String taskDescription) {
        return repository.save(new Task(taskName, taskDescription));
    }

    @Transactional(readOnly = true)
    public Task findTaskById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la tarea con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Task> findTaskByName(String taskName) {
        if (taskName == null || taskName.isBlank()) {
            throw new IllegalArgumentException("El nombre de la tarea no puede estar vacío");
        }
        return repository.findByTaskName(taskName);
    }

    @Transactional(readOnly = true)
    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    @Transactional
    public Task updateTask(int id, String taskName, String taskDescription) {
        Task task = findTaskById(id);
        task.setTaskName(taskName);
        task.setTaskDescription(taskDescription);
        return repository.save(task);
    }

    @Transactional
    public void deleteTask(int id) {
        Task task = findTaskById(id);
        repository.delete(task);
    }

}
