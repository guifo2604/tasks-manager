package br.com.projeto.gerenciador_de_tarefas.repositories;

import br.com.projeto.gerenciador_de_tarefas.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository <UserLogin, Long>{
    Optional<UserLogin> findByEmail(String email);
}
