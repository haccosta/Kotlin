package com.costa.handerson.resfulcomkotlin.controller

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoAtualizacaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoCategoriaDTO
import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import com.costa.handerson.resfulcomkotlin.services.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearerAuth")
class TopicoController(
    private val topicoService: TopicoService,
) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable,
    ): Page<RespostaTopicoDTO> {
        return topicoService.listar(nomeCurso, paginacao);
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): RespostaTopicoDTO {
        return topicoService.buscarPorId(id);

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
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
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(
        @RequestBody @Valid topico: RequisicaoAtualizacaoTopicoDTO,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<RespostaTopicoDTO> {
        val respostaTopico = topicoService.atualizar(topico)
        return ResponseEntity.ok(respostaTopico)

    }

    @DeleteMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }

    @GetMapping("/relatorio")
    fun gerarRelatorio(): List<RespostaTopicoCategoriaDTO> {
        return topicoService.gerarRelatorio();
    }
}