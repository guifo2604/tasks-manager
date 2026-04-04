package br.com.projeto.gerenciador_de_tarefas.dto;

public record LoginResponse(

        UserResponse user,
        String token
) {

}
