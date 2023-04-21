package com.costa.handerson.resfulcomkotlin.mapper

import com.costa.handerson.resfulcomkotlin.dto.RespostaTopicoDTO
import com.costa.handerson.resfulcomkotlin.model.Topico
import org.springframework.stereotype.Component

@Component
class RespostaTopicoMapper : ConverterMapper<Topico, RespostaTopicoDTO> {
    override fun mapper(t: Topico): RespostaTopicoDTO {

        return RespostaTopicoDTO(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao
        )

    }

}