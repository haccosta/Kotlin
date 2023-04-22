package com.costa.handerson.resfulcomkotlin.services

import com.costa.handerson.resfulcomkotlin.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val usuario : Usuario
):UserDetails {
    override fun getAuthorities() = usuario.role

    override fun getPassword() = this.usuario.password

    override fun getUsername() = this.usuario.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}