package br.com.projeto.gerenciador_de_tarefas.services;

import br.com.projeto.gerenciador_de_tarefas.dto.LoginRequest;
import br.com.projeto.gerenciador_de_tarefas.models.User;
import br.com.projeto.gerenciador_de_tarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import br.com.projeto.gerenciador_de_tarefas.infrastructure.security.SecurityConfig;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User login(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password invalids"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password invalids");
        }
        return user;
    }

    public void deleteUser(Long id){
        var optionalUser = getUserById(id);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userData.getUsername());
                    user.setEmail(userData.getEmail());
                    if (userData.getPassword() != null && !userData.getPassword().isBlank()) {
                        user.setPassword(passwordEncoder.encode(userData.getPassword()));
                    }
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }



}
