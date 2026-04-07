package br.com.projeto.gerenciador_de_tarefas.dto;

import br.com.projeto.gerenciador_de_tarefas.enums.Status;
import br.com.projeto.gerenciador_de_tarefas.models.Task;

import java.time.LocalDate;

public record TaskResponse(

        Long id,
        String title,
        String caption,
        String content,
        Status status,
        LocalDate dataInicial,
        LocalDate dataFinal,
        Long userId,
        String userName
) {

    public static TaskResponse fromEntity(Task t){
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getCaption(),
                t.getContent(),
                t.getStatus(),
                t.getDataInicial(),
                t.getDataFinal(),
                t.getUser().getIdUser(),
                t.getUser().getUsername()
        );
    }
}
