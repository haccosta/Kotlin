package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.model.Curso
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService(
    var cursos: List<Curso>
) {
    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Categoria 1"
        )

        cursos = Arrays.asList(curso);
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream()
            .filter({ curso ->
                curso.id == id
            }).findFirst().get()
    }
}