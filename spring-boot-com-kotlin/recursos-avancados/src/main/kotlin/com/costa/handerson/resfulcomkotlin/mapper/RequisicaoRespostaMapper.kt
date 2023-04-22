package com.costa.handerson.resfulcomkotlin.mapper

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoRespostaDTO
import com.costa.handerson.resfulcomkotlin.model.Resposta
import com.costa.handerson.resfulcomkotlin.services.TopicoService
import com.costa.handerson.resfulcomkotlin.services.UsuarioService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RequisicaoRespostaMapper (
    private val usuarioService: UsuarioService,
    private val topicoService: TopicoService,
) : ConverterMapper<RequisicaoRespostaDTO, Resposta>  {

    override fun mapper(t: RequisicaoRespostaDTO) =
        Resposta(
            mensagem = t.mensagem,
            dataCriacao = LocalDateTime.now(),
            autor = usuarioService.buscarPorId(t.idAutor),
            topico = topicoService.buscarPorIdParaResposta(t.idTopico),
            solucao = t.solucao

        )

}