package br.com.alura.bytebank.modelo

import br.com.alura.bytebank.modelo.Autenticavel

class SistemaInterno {
    fun autenticar(autenticavel: Autenticavel, senha: Int) {
        if (autenticavel.autenticar(senha)) {
            println("Logado com sucesso");
        } else {
            println("Erro de autenticação");
        }

    }
}