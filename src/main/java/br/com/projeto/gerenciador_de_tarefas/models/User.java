package br.com.projeto.gerenciador_de_tarefas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotBlank(message = "O nome de usuário nao pode ser em branco!")
    @Size(min = 3, max = 50, message = "O nome de usuário deve conter entre 3 e 50 caracteres")
    private String username;

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "Formato de email invalido")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> tasks;

    @Transient
    private String password;
}
