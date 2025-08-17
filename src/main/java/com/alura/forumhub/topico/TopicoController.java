package com.alura.forumhub.topico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        var topicoCadastrado = service.cadastrarTopico(dados);
        // Retornar 201 Created com os dados do tópico criado
        return ResponseEntity.status(201).body(topicoCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = service.listarTopicosAtivos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemTopico> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topicoAtualizado = service.atualizarTopico(id, dados);
        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirTopico(id);
        // Retornar 204 No Content, o padrão para exclusão bem-sucedida
        return ResponseEntity.noContent().build();
    }
}