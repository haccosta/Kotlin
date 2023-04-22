package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.model.Usuario
import com.costa.handerson.resfulcomkotlin.repository.UsuarioRepostiory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repostiory: UsuarioRepostiory,
) : UserDetailsService {


    fun buscarPorId(id: Long): Usuario {
        return repostiory.getOne(id);
    }

    override fun loadUserByUsername(userName: String?): UserDetails {
        val usuario = repostiory.findByEmail(userName) ?: throw RuntimeException("Usuário não encontrado")
        return UserDetail(usuario)

    }
}