package br.com.projeto.gerenciador_de_tarefas.controllers;


import br.com.projeto.gerenciador_de_tarefas.dto.LoginRequest;
import br.com.projeto.gerenciador_de_tarefas.dto.UserRequest;
import br.com.projeto.gerenciador_de_tarefas.dto.UserResponse;
import br.com.projeto.gerenciador_de_tarefas.models.User;
import br.com.projeto.gerenciador_de_tarefas.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(UserResponse.fromEntity(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest){
        User savedUser = userService.addUser(userRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromEntity(savedUser));
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        User user = userService.login(loginRequest);
        return ResponseEntity.ok(UserResponse.fromEntity(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User user = userService.updateUser(id, userRequest.toEntity());
        return ResponseEntity.ok(UserResponse.fromEntity(user));
    }



}
