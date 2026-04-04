package br.com.projeto.gerenciador_de_tarefas.models;

import br.com.projeto.gerenciador_de_tarefas.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
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
    private LocalDate dataInicial;
    @FutureOrPresent(message = "A data final não pode ser no passado")
    private LocalDate dataFinal;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
