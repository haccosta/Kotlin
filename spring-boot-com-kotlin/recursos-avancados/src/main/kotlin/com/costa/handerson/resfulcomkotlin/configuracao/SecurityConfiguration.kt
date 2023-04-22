package com.costa.handerson.resfulcomkotlin.configuracao

import com.costa.handerson.resfulcomkotlin.security.JWTAutenticationFilter
import com.costa.handerson.resfulcomkotlin.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.
        csrf()?.disable()?.
        authorizeHttpRequests()?.
        antMatchers("/topicos")?.hasAuthority("LEITURA_ESCRITA")?.
        antMatchers("/respostas")?.hasAuthority("LEITURA_ESCRITA")?.
        antMatchers("/relatorios")?.hasAuthority("ADMIN")?.
        antMatchers(HttpMethod.GET, "/swagger-ui/*")?.permitAll()?.
        antMatchers(HttpMethod.GET, "/v3/api-docs/**")?.permitAll()?.
        antMatchers(HttpMethod.POST,"/login")?.permitAll()?.
        anyRequest()?.
        authenticated()?.
        and()
        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = this.jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.addFilterBefore(JWTAutenticationFilter(jwtUtil = this.jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder();
    }
}