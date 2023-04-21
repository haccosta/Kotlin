package com.costa.handerson.resfulcomkotlin.repository

import com.costa.handerson.resfulcomkotlin.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {
}