package com.costa.handerson.resfulcomkotlin.repository

import com.costa.handerson.resfulcomkotlin.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepostiory : JpaRepository<Usuario, Long> {

    fun findByEmail(userName: String?): Usuario?
}