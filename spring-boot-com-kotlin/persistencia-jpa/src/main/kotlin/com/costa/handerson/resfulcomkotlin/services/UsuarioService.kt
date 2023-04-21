package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.model.Usuario
import com.costa.handerson.resfulcomkotlin.repository.UsuarioRepostiory
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UsuarioService(
    private val repostiory: UsuarioRepostiory,
) {


    fun buscarPorId(id: Long): Usuario {
        return repostiory.getOne(id);
    }
}