package br.com.projeto.gerenciador_de_tarefas.services;

import br.com.projeto.gerenciador_de_tarefas.models.Task;
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
    private TaskRepository repository;

    public List<Task> getTask(){ return repository.findAll(); }



    public Task addTask(Task task){
        repository.save(task);
        return task;
    }

    public Optional<Task> getTaskById(Long id){
        return repository.findById(id);

    }


    public void deletTask(Long id){
        var optionalTask = getTaskById(id);
        if(optionalTask.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa Não encontrada");
        }
        repository.deleteById(id);
    }

    public Task updateTask(Long id, Task newTask){
        var optionalTask = getTaskById(id);
        if(optionalTask.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        // var task = optionalTask.get();
        // BeanUtils.copyProperties(newTask,task,"id");
        newTask.setId(id);
        return repository.save(newTask);


    }




}
