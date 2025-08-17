package com.alura.forumhub.topico;


public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        Boolean status) {
}