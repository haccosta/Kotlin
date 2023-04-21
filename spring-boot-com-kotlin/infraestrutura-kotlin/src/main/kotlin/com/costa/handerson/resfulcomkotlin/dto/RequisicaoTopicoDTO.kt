package com.costa.handerson.resfulcomkotlin.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RequisicaoTopicoDTO(
    @field:NotEmpty @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres") val titulo: String,
    @field:NotEmpty val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
) {

}
