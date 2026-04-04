package br.com.projeto.gerenciador_de_tarefas.repositories;

import br.com.projeto.gerenciador_de_tarefas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String email);
}
