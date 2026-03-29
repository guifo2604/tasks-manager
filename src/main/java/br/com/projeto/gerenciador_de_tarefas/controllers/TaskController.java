package br.com.projeto.gerenciador_de_tarefas.controllers;


import br.com.projeto.gerenciador_de_tarefas.models.Task;
import br.com.projeto.gerenciador_de_tarefas.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/user/{userId}")
    public List<Task> getTasks(@PathVariable Long userId){
        return service.getTaskByUser(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Task> addTask(@RequestBody Task task, @PathVariable Long userId){
        var tasks = service.addTask(task,userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return service.getTaskById(id)
                .map((t) -> ResponseEntity.ok(t))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @PathVariable Long userId){
        service.deleteTask(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/user/{userId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @PathVariable Long userId, @RequestBody Task newTask){
        Task task = service.updateTask(id, newTask, userId);
        return ResponseEntity.ok(task);
        }

}
