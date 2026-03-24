package br.com.projeto.gerenciador_de_tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Ele já faz o Scan de Repositories e Entities automaticamente
public class GerenciadorDeTarefasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorDeTarefasApplication.class, args);
    }
}