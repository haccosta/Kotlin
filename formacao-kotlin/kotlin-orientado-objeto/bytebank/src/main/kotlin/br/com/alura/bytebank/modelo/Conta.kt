package br.com.alura.bytebank.modelo

import br.com.alura.bytebank.exception.SaldoInsuficienteException

abstract class Conta(
    val titular: Cliente,
    val numeroConta: Int
) {
    var saldo = 0.00
        protected set

    companion object {
        var total: Int = 0
            private set
    }

    init {
        total++;
    }

    fun depositar(valor: Double) {
        this.saldo += valor;
    }

    abstract fun sacar(valor: Double)

    fun transferir(valor: Double, contaDestino: Conta) {

        if (this.saldo < valor) {
            throw SaldoInsuficienteException(mensagem = "Saldo Insuficiente");
        }
        sacar(valor)
        contaDestino.depositar(valor)


    }

}