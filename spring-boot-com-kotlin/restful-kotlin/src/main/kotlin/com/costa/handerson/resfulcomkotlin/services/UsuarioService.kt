package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.model.Usuario
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UsuarioService(
    var autores: List<Usuario>
) {
    init {
        val autor = Usuario(
            id = 1,
            nome = "Usuario 1",
            email = "email@email.com"
        )

        autores = Arrays.asList(autor);
    }

    fun buscarPorId(id: Long): Usuario {
        return autores.stream()
            .filter({ curso ->
                curso.id == id
            }).findFirst().get()
    }
}