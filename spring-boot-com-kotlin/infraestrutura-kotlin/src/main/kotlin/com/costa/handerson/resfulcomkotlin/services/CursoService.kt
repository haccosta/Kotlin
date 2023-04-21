package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.model.Curso
import com.costa.handerson.resfulcomkotlin.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val repository: CursoRepository,
) {

    fun buscarPorId(id: Long): Curso {
        return repository.getOne(id);
    }
}