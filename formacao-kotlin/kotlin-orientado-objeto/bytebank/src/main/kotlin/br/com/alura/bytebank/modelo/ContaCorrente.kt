package br.com.alura.bytebank.modelo

class ContaCorrente(
    titular: Cliente,
    numeroConta: Int
) : Conta(
    titular = titular,
    numeroConta = numeroConta
) {
    override fun sacar(valor: Double) {
        val valorTotalSaque = valor + 0.1
        if(this.saldo >= valorTotalSaque){
            this.saldo -= valorTotalSaque
        }
    }
}