package br.com.projeto.gerenciador_de_tarefas.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER_LOGIN")
public class UserLogin {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name =  "idUser")
    private User user;
}
