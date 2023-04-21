package com.costa.handerson.resfulcomkotlin.service

import com.costa.handerson.resfulcomkotlin.exception.NotFoundException
import com.costa.handerson.resfulcomkotlin.mapper.RequisicaoTopicoMapper
import com.costa.handerson.resfulcomkotlin.mapper.RespostaTopicoMapper
import com.costa.handerson.resfulcomkotlin.model.RespostaTopicoDTOTest
import com.costa.handerson.resfulcomkotlin.model.TopicoTest
import com.costa.handerson.resfulcomkotlin.repository.TopicoRepository
import com.costa.handerson.resfulcomkotlin.services.TopicoService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val topicosMock = PageImpl(listOf(TopicoTest().builder()))

    val paginacaoMock: Pageable = mockk()

    val repository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicosMock
        every { findAll(paginacaoMock) } returns topicosMock
    }
    val respostaTopicoMapper: RespostaTopicoMapper = mockk {
        every { mapper(any()) } returns RespostaTopicoDTOTest().builder()

    }

    val requiscaoTopicoMapper: RequisicaoTopicoMapper = mockk()

    val topicoService = TopicoService(
        repository,
        respostaTopicoMapper,
        requiscaoTopicoMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.listar("Curso 1", paginacaoMock)
        verify(exactly = 1) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { respostaTopicoMapper.mapper(any()) }
        verify(exactly = 0) { repository.findAll(paginacaoMock) }
    }

    @Test
    fun `deve lista todos os topicos a partir do nome do curso`() {
        topicoService.listar(null, paginacaoMock)

        verify(exactly = 0) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { respostaTopicoMapper.mapper(any()) }
        verify(exactly = 1) { repository.findAll(paginacaoMock) }

    }

    @Test
    fun `deve listar not found exception quando topico nao for encontrado`() {
        every { repository.findById(any()) } returns Optional.empty()
        val resposta = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }

        assertThat(resposta.message).isEqualTo("Topico nao encontrado!")
    }


}