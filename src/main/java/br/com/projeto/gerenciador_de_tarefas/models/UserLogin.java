package br.com.projeto.gerenciador_de_tarefas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER_LOGIN")
public class UserLogin {

    @Id
    private Long id;

    @NotBlank(message = "O email nao pode estar em branco")
    @Email(message = "o email esta com formato invalido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A nao pode estar vazia")
    @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
    @Column(nullable = false)
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name =  "idUser")
    private User user;
}
