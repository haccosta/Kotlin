package com.costa.handerson.resfulcomkotlin.mapper

import com.costa.handerson.resfulcomkotlin.dto.RequisicaoTopicoDTO
import com.costa.handerson.resfulcomkotlin.model.Topico
import com.costa.handerson.resfulcomkotlin.services.CursoService
import com.costa.handerson.resfulcomkotlin.services.UsuarioService
import org.springframework.stereotype.Component

@Component
class RequisicaoTopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,

) : ConverterMapper<RequisicaoTopicoDTO, Topico> {


    override fun mapper(t: RequisicaoTopicoDTO): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)

        )
    }
}