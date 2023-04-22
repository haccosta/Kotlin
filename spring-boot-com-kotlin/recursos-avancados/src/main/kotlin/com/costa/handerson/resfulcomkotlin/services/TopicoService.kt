package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoAtualizacaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoCategoriaDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import com.costa.handerson.resfulcomkotlin.exception.NotFoundException
import com.costa.handerson.resfulcomkotlin.mapper.RequisicaoTopicoMapper
import com.costa.handerson.resfulcomkotlin.mapper.RespostaTopicoMapper
import com.costa.handerson.resfulcomkotlin.model.Topico
import com.costa.handerson.resfulcomkotlin.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val respostaTopicoMapper: RespostaTopicoMapper,
    private val requisicaoTopicoMapper: RequisicaoTopicoMapper,
    private val notFoundMessage: String = "Topico nao encontrado!",
) {
    @Cacheable(cacheNames = ["topicos"], key = "#root.method.name")
    fun listar(nomeCurso: String?, paginacao: Pageable): Page<RespostaTopicoDTO> {
        val topicos = if (nomeCurso == null) {
            this.repository.findAll(paginacao)
        } else {
            this.repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map({ topico -> respostaTopicoMapper.mapper(topico) })
    }

    fun buscarPorId(id: Long): RespostaTopicoDTO {

        return this.repository.findById(id).stream().filter { topico ->
            topico.id == id
        }.findFirst().map({ topico -> respostaTopicoMapper.mapper(topico) })
            .orElseThrow { NotFoundException(notFoundMessage) }

    }

    fun buscarPorIdParaResposta(id: Long): Topico {

        return this.repository.findById(id).stream().filter { topico ->
            topico.id == id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }

    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(requisicao: RequisicaoTopicoDTO): RespostaTopicoDTO {
        val topico = requisicaoTopicoMapper.mapper(requisicao)
        this.repository.save(topico)
        return respostaTopicoMapper.mapper(topico);
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(requisicao: RequisicaoAtualizacaoTopicoDTO): RespostaTopicoDTO {
        val topicoEncontrado = this.repository.findById(requisicao.id).stream().filter { topico ->
            topico.id == requisicao.id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }

        topicoEncontrado.titulo = requisicao.titulo
        topicoEncontrado.mensagem = requisicao.mensagem
        topicoEncontrado.dataAlteracao = LocalDateTime.now()


        return respostaTopicoMapper.mapper(topicoEncontrado)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(id: Long) {
        this.repository.deleteById(id)
    }

    fun gerarRelatorio(): List<RespostaTopicoCategoriaDTO> {
        return repository.buscarRelatorioTopicoPorCategoria();
    }
}