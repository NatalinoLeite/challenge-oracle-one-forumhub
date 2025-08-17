package com.alura.forumhub.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.status = true;
        this.dataCriacao = LocalDateTime.now();
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
    }

    // Novo método para a exclusão lógica
    public void inativar() {
        this.status = false;
    }
}