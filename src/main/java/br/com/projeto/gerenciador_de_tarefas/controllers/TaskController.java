package br.com.projeto.gerenciador_de_tarefas.controllers;


import br.com.projeto.gerenciador_de_tarefas.dto.TaskRequest;
import br.com.projeto.gerenciador_de_tarefas.dto.TaskResponse;
import br.com.projeto.gerenciador_de_tarefas.models.Task;
import br.com.projeto.gerenciador_de_tarefas.repositories.TaskRepository;
import br.com.projeto.gerenciador_de_tarefas.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponse>> getTasks(@PathVariable Long userId){
    List<Task> tasks = taskService.getTaskByUser(userId);
    List<TaskResponse> response = tasks.stream()
            .map(TaskResponse::fromEntity)
            .toList();
    return ResponseEntity.ok(response);

    }

    @PostMapping("/{userId}")
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest taskRequest, @PathVariable Long userId){
        var task = taskRequest.toEntity();

        var tasks = taskService.addTask(task,userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TaskResponse.fromEntity(tasks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map((t) -> ResponseEntity.ok(TaskResponse.fromEntity(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @PathVariable Long userId){
        taskService.deleteTask(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/user/{userId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @PathVariable Long userId, @RequestBody @Valid TaskRequest newTaskRequest){
        Task task = taskService.updateTask(id, newTaskRequest.toEntity(), userId);
        return ResponseEntity.ok(TaskResponse.fromEntity(task));
        }

}
