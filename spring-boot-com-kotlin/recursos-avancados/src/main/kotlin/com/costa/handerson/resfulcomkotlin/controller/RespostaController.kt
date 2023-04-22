package com.costa.handerson.resfulcomkotlin.controller

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoRespostaDTO
import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.model.Resposta
import com.costa.handerson.resfulcomkotlin.services.RespostaService
import com.costa.handerson.resfulcomkotlin.services.TopicoService
import com.costa.handerson.resfulcomkotlin.services.UsuarioService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearerAuth")
class RespostasController(
    private val service: RespostaService,
) {

    @PostMapping
    fun salvar(@RequestBody @Valid requisicao: RequisicaoRespostaDTO) = service.salvar(requisicao)

}