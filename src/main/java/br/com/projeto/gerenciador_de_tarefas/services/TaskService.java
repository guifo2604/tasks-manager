package br.com.projeto.gerenciador_de_tarefas.services;

import br.com.projeto.gerenciador_de_tarefas.models.Task;
import br.com.projeto.gerenciador_de_tarefas.models.User;
import br.com.projeto.gerenciador_de_tarefas.services.UserService;
import br.com.projeto.gerenciador_de_tarefas.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository repository;

    public List<Task> getTaskByUser(Long userId){
        return repository.findBy_IdUser(userId);
    }

    public Task addTask(@org.jetbrains.annotations.NotNull Task task, Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        task.setUser(user);
        return repository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return repository.findById(id);

    }


    public void deleteTask(Long id, Long userId){
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada!"));
        if(!task.getUser().getIdUser().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Voce nao tem permissao para deletar a task!");
        }
        repository.delete(task);
    }

    public Task updateTask(Long id, Task newTask, Long userId){
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada!"));
        if(!task.getUser().getIdUser().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Voce nao  tem permissao para atualizar a task!");
        }
        task.setTitle(newTask.getTitle());
        task.setCaption(newTask.getCaption());
        task.setContent(newTask.getContent());
        task.setStatus(newTask.getStatus());
        task.setDataInicial(newTask.getDataInicial());
        task.setDataFinal(newTask.getDataFinal());

        return repository.save(task);


    }




}
