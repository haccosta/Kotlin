package com.costa.handerson.resfulcomkotlin.configuracao

import com.costa.handerson.resfulcomkotlin.services.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val usuarioService: UsuarioService,
) {

    private val expiration: Long = 600000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: List<out GrantedAuthority>): String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + this.expiration))
            .signWith(SignatureAlgorithm.HS512, this.secret.toByteArray())
            .compact();
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(this.secret.toByteArray()).parseClaimsJws(jwt)
            return true
        } catch (e: RuntimeException) {
            println(e.stackTrace)
            false
        }

    }

    fun getAuthetication(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(this.secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val usuarioEncontrado = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, usuarioEncontrado.authorities)
    }
}