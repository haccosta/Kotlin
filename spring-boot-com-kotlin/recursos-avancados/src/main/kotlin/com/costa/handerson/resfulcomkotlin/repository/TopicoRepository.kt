package com.costa.handerson.resfulcomkotlin.repository

import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoCategoriaDTO
import com.costa.handerson.resfulcomkotlin.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String, paginacao : Pageable): Page<Topico>
    @Query("SELECT new com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoCategoriaDTO(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun buscarRelatorioTopicoPorCategoria(): List<RespostaTopicoCategoriaDTO>
}