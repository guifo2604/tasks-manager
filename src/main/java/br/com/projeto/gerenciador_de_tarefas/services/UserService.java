package br.com.projeto.gerenciador_de_tarefas.services;

import br.com.projeto.gerenciador_de_tarefas.models.User;
import br.com.projeto.gerenciador_de_tarefas.models.UserLogin;
import br.com.projeto.gerenciador_de_tarefas.repositories.UserLoginRepository;
import br.com.projeto.gerenciador_de_tarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository loginRepository;

    @Transactional
    public User addUser(User user, String password){
        User savedUser = userRepository.save(user);

        UserLogin login = new UserLogin();
        login.setUser(savedUser);
        login.setEmail(savedUser.getEmail());
        login.setPassword(password);

        loginRepository.save(login);
        return savedUser;
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<UserLogin> getUserByEmail(String email){
        return loginRepository.findByEmail(email);
    }

    public User login(String email, String password){
        var optionalUser = getUserByEmail(email);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado, e-mail ou senha incorretos");
        }
        UserLogin login = optionalUser.get();
        if(!login.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta, tente novamente!");
        }
        return login.getUser();

    }

    public void deleteUser(Long id){
        var optionalUser = getUserById(id);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        userRepository.deleteById(id);
        loginRepository.deleteById(id);
    }

    public User updateUser(Long id, User newUser){
        var optionalUser = getUserById(id);
        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        newUser.setIdUser(id);
        return userRepository.save(newUser);
    }



}
