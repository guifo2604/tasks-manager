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

    @GetMapping
    public List<Task> getTasks(){
        return service.getTask();
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task, Long userId){
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        service.deletTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task newTask){
        Task task = service.updateTask(id, newTask);
        return ResponseEntity.ok(task);
    }

}
