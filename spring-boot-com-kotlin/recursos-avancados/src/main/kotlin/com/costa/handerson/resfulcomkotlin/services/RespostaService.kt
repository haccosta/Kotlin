package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoRespostaDTO
import com.costa.handerson.resfulcomkotlin.mapper.RequisicaoRespostaMapper
import com.costa.handerson.resfulcomkotlin.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val repository: RespostaRepository,
    private val emailService: EmailService,
    private val respostaMapper: RequisicaoRespostaMapper
) {

    fun salvar(requisicao: RequisicaoRespostaDTO) {
        val resposta = respostaMapper.mapper(requisicao)
        repository.save(resposta)
        val autor = resposta.autor.email
        emailService.enviarEmail(autor)
    }


}