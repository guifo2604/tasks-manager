package br.com.projeto.gerenciador_de_tarefas.dto;

import br.com.projeto.gerenciador_de_tarefas.models.User;

public record UserResponse(

    Long idUser,
    String username,
    String email

) {

    public static UserResponse fromEntity(User u){
        return new UserResponse(
                u.getIdUser(),
                u.getUsername(),
                u.getEmail()
        );
    }
}
