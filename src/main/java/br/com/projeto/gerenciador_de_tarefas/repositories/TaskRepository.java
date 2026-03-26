package br.com.projeto.gerenciador_de_tarefas.repositories;

import br.com.projeto.gerenciador_de_tarefas.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

}
