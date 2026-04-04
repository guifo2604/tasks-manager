package br.com.projeto.gerenciador_de_tarefas.services;

import br.com.projeto.gerenciador_de_tarefas.models.User;
import br.com.projeto.gerenciador_de_tarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);

    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User login(String email, String password){
        var optionalUser = getUserByEmail(email);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado, e-mail ou senha incorretos");
        }
        User loginU = optionalUser.get();
        if(!loginU.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta, tente novamente!");
        }
        return loginU;

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
                        user.setPassword(userData.getPassword());
                    }
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }



}
