package com.costa.handerson.resfulcomkotlin.dto

import java.time.LocalDateTime

data class RespostaErro(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val mensagem: String?,
    val erro: String,
    val path: String
) {
}