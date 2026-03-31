package br.com.projeto.gerenciador_de_tarefas.models;

import br.com.projeto.gerenciador_de_tarefas.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "O titulo nao pode ser vazio!")
    private String title;
    @NotBlank(message = "A legenda / subtitulo não pode ser em branco!")
    private String caption;
    @NotBlank(message = "O conteudo não pode estar em branco!")
    private String content;

    @NotNull(message = "O status da tarefa não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private Status status;
    @FutureOrPresent(message = "A data inicial não pode ser no passado")
    private LocalDateTime dataInicial;
    @FutureOrPresent(message = "A data final não pode ser no passado")
    private LocalDateTime dataFinal;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
