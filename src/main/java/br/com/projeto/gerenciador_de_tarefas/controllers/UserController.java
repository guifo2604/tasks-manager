package br.com.projeto.gerenciador_de_tarefas.controllers;


import br.com.projeto.gerenciador_de_tarefas.models.User;
import br.com.projeto.gerenciador_de_tarefas.models.UserLogin;
import br.com.projeto.gerenciador_de_tarefas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserService loginService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map((u) ->ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        var users = userService.addUser(user, user.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }




}
