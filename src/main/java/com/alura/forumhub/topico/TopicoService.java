package com.alura.forumhub.topico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    public DadosListagemTopico cadastrarTopico(DadosCadastroTopico dados) {
        if (repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem()).isPresent()) {
            throw new RuntimeException("Tópico duplicado: Já existe um tópico com o mesmo título e mensagem.");
        }

        var topico = new Topico(dados.titulo(), dados.mensagem(), dados.autor(), dados.curso());
        repository.save(topico);

        return new DadosListagemTopico(topico);
    }

    public Page<DadosListagemTopico> listarTopicosAtivos(Pageable paginacao) {
        return repository.findAllByStatusTrue(paginacao).map(DadosListagemTopico::new);
    }

    public DadosListagemTopico detalharTopico(Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com id: " + id));
        return new DadosListagemTopico(topico);
    }

    public DadosListagemTopico atualizarTopico(Long id, DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(id);
        topico.atualizarInformacoes(dados);
        return new DadosListagemTopico(topico);
    }

    public void excluirTopico(Long id) {
        var topico = repository.getReferenceById(id);
        topico.inativar();
    }
}