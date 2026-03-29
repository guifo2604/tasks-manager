package br.com.projeto.gerenciador_de_tarefas.repositories;
import br.com.projeto.gerenciador_de_tarefas.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user.idUser = :userId")
    List<Task> findBy_IdUser(@Param("userId") Long userId);
}