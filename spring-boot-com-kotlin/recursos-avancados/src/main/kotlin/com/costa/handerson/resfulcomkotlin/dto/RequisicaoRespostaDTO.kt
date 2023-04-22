package com.costa.handerson.resfulcomkotlin.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RequisicaoRespostaDTO (
    @field:NotEmpty @field:Size(min = 5, max = 100, message = "Mensagem deve ter entre 5 e 100 caracteres") val mensagem: String,
    @field:NotNull val idTopico: Long,
    @field:NotNull val idAutor: Long,
    @field:NotNull val solucao : Boolean
)