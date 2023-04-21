package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoAtualizacaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import com.costa.handerson.resfulcomkotlin.exception.NotFoundException
import com.costa.handerson.resfulcomkotlin.mapper.RequisicaoTopicoMapper
import com.costa.handerson.resfulcomkotlin.mapper.RespostaTopicoMapper
import com.costa.handerson.resfulcomkotlin.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val respostaTopicoMapper: RespostaTopicoMapper,
    private val requisicaoTopicoMapper: RequisicaoTopicoMapper,
    private val notFoundMessage: String = "Topico nao encontrado!"
) {

    fun listar(): List<RespostaTopicoDTO> {
        return this.topicos.stream()
            .map({ topico ->
                respostaTopicoMapper.mapper(topico)
            }).collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): RespostaTopicoDTO {

        return topicos.stream().filter({ topico ->
            topico.id == id
        }).findFirst().map({ topico -> respostaTopicoMapper.mapper(topico) }).orElseThrow{NotFoundException(notFoundMessage)}

    }

    fun cadastrar(requisicao: RequisicaoTopicoDTO): RespostaTopicoDTO {
        val topico = requisicaoTopicoMapper.mapper(requisicao)
        topico.id = topicos.size.toLong() + 1
        this.topicos = this.topicos.plus(topico)

        return respostaTopicoMapper.mapper(topico);
    }

    fun atualizar(requisicao: RequisicaoAtualizacaoTopicoDTO): RespostaTopicoDTO {
        val topicoEncontrado = topicos.stream().filter({ topico ->
            topico.id == requisicao.id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        val topicoAtualizado = Topico(
            id = requisicao.id,
            titulo = requisicao.titulo,
            mensagem = requisicao.mensagem,
            autor = topicoEncontrado.autor,
            curso = topicoEncontrado.curso,
            resposta = topicoEncontrado.resposta,
            dataCriacao = topicoEncontrado.dataCriacao
        )

        topicos = topicos.minus(topicoEncontrado).plus(topicoAtualizado)

        return respostaTopicoMapper.mapper(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topicoEncontrado = topicos.stream().filter({ topico ->
            topico.id == id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        topicos = topicos.minus(topicoEncontrado);
    }
}