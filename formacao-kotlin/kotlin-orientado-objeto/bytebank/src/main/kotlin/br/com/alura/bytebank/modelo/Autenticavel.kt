package br.com.alura.bytebank.modelo

interface Autenticavel {
    fun autenticar(senha: Int): Boolean
}