package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoAtualizacaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoCategoriaDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import com.costa.handerson.resfulcomkotlin.exception.NotFoundException
import com.costa.handerson.resfulcomkotlin.mapper.RequisicaoTopicoMapper
import com.costa.handerson.resfulcomkotlin.mapper.RespostaTopicoMapper
import com.costa.handerson.resfulcomkotlin.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val respostaTopicoMapper: RespostaTopicoMapper,
    private val requisicaoTopicoMapper: RequisicaoTopicoMapper,
    private val notFoundMessage: String = "Topico nao encontrado!",
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<RespostaTopicoDTO> {
        val topicos = if (nomeCurso == null) {
            this.repository.findAll(paginacao)
        } else {
            this.repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map({ topico -> respostaTopicoMapper.mapper(topico) })
    }

    fun buscarPorId(id: Long): RespostaTopicoDTO {

        return this.repository.findById(id).stream().filter({ topico ->
            topico.id == id
        }).findFirst().map({ topico -> respostaTopicoMapper.mapper(topico) })
            .orElseThrow { NotFoundException(notFoundMessage) }

    }

    fun cadastrar(requisicao: RequisicaoTopicoDTO): RespostaTopicoDTO {
        val topico = requisicaoTopicoMapper.mapper(requisicao)
        this.repository.save(topico)
        return respostaTopicoMapper.mapper(topico);
    }

    fun atualizar(requisicao: RequisicaoAtualizacaoTopicoDTO): RespostaTopicoDTO {
        val topicoEncontrado = this.repository.findById(requisicao.id).stream().filter({ topico ->
            topico.id == requisicao.id
        }).findFirst().orElseThrow { NotFoundException(notFoundMessage) }

        topicoEncontrado.titulo = requisicao.titulo
        topicoEncontrado.mensagem = requisicao.mensagem


        return respostaTopicoMapper.mapper(topicoEncontrado)
    }

    fun deletar(id: Long) {
        this.repository.deleteById(id)
    }

    fun gerarRelatorio() : List<RespostaTopicoCategoriaDTO> {
       return repository.buscarRelatorioTopicoPorCategoria();
    }
}