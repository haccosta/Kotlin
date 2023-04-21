package com.costa.handerson.resfulcomkotlin.controller

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoAtualizacaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import com.costa.handerson.resfulcomkotlin.services.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val topicoService: TopicoService,
) {

    @GetMapping
    fun listar(): List<RespostaTopicoDTO> {
        return topicoService.listar();
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): RespostaTopicoDTO {
        return topicoService.buscarPorId(id);

    }

    @PostMapping
    fun cadastrar(
        @RequestBody
        @Valid topico: RequisicaoTopicoDTO,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<RespostaTopicoDTO> {

        val respostaTopico = topicoService.cadastrar(topico)
        val uri = uriBuilder.path("/topicos/{${respostaTopico.id}}").build().toUri();

        return ResponseEntity.created(uri).body(respostaTopico)
    }

    @PutMapping
    fun atualizar(
        @RequestBody @Valid topico: RequisicaoAtualizacaoTopicoDTO,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<RespostaTopicoDTO> {
        val respostaTopico = topicoService.atualizar(topico)
        return ResponseEntity.ok(respostaTopico)

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }
}