package br.com.projeto.gerenciador_de_tarefas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank @Email
        String email,
        @NotBlank
        String password

){

}
