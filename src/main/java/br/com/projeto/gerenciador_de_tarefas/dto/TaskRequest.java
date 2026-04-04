package br.com.projeto.gerenciador_de_tarefas.dto;

import br.com.projeto.gerenciador_de_tarefas.enums.Status;
import br.com.projeto.gerenciador_de_tarefas.models.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskRequest(

        @NotBlank(message = "O titulo nao pode ser vazio!")
        String title,

        @NotBlank(message = "A legenda / subtitulo não pode ser em branco!")
        String caption,

        @NotBlank(message = "O conteudo não pode estar em branco!")
        String content,

        @NotNull(message = "O status da tarefa não pode ser nulo")
        Status status,

        @FutureOrPresent(message = "A data inicial não pode ser no passado")
        LocalDate dataInicial,

        @FutureOrPresent(message = "A data inicial não pode ser no passado")
        LocalDate dataFinal


) {
    public Task toEntity() {
    return Task.builder()
            .title(title)
            .caption(caption)
            .content(content)
            .status(status)
            .dataInicial(dataInicial)
            .dataFinal(dataFinal)
            .build();


    }
}
