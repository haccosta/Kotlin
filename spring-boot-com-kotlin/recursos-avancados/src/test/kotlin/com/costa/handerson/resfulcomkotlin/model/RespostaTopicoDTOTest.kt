package com.costa.handerson.resfulcomkotlin.model

import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import java.time.LocalDateTime

class RespostaTopicoDTOTest {
    fun builder() = RespostaTopicoDTO(
        titulo = "Titulo 1",
        mensagem = "Mensagem 1",
        id = 1,
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDateTime.now()
    )
}