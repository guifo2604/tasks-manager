package br.com.projeto.gerenciador_de_tarefas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_TASK")
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String caption;
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
