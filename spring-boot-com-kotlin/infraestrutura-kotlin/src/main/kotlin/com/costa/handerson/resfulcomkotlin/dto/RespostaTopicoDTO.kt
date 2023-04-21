package com.costa.handerson.resfulcomkotlin.dto

import com.costa.handerson.resfulcomkotlin.model.StatusTopico
import java.time.LocalDateTime

data class RespostaTopicoDTO(
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime,
) {

}