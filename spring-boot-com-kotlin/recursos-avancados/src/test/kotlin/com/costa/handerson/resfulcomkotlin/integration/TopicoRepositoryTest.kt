package com.costa.handerson.resfulcomkotlin.integration

import com.costa.handerson.resfulcomkotlin.configuracao.DatabaseContainerConfiguration
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoCategoriaDTO
import com.costa.handerson.resfulcomkotlin.model.TopicoTest
import com.costa.handerson.resfulcomkotlin.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.data.domain.PageRequest

import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository
    private val topico = TopicoTest().builder()

   @Test
    fun `deve gerar um relatorio`() {

        topicoRepository.save(topico)
        val relatorio = topicoRepository.buscarRelatorioTopicoPorCategoria()

        assertThat(relatorio).isNotNull()
        assertThat(relatorio.first()).isExactlyInstanceOf(RespostaTopicoCategoriaDTO::class.java)

    }

    @Test
    fun `deve listar topico por nome de curso `() {

        topicoRepository.save(topico)
        val resultado = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))

        assertThat(resultado).isNotNull()
    }
}