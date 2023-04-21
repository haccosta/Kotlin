package br.com.alura.bytebank.teste

import br.com.alura.bytebank.exception.SaldoInsuficienteException
import br.com.alura.bytebank.modelo.*

fun testaContasDiferentes() {

    val alex = Cliente(nome = "Alex", cpf = "", senha = 1, Endereco(logradouro = "Teste"))

    val contaCorrente = ContaCorrente(
        titular = alex,
        numeroConta = 1000
    )
    val fran = Cliente(nome = "Fran", cpf = "", senha = 2)
    val contaPoupanca = ContaPoupanca(
        titular = fran,
        numeroConta = 1001
    )

    contaCorrente.depositar(90.0)
    contaPoupanca.depositar(1000.0)

    println("saldo corrente: ${contaCorrente.saldo}")
    println("saldo poupança: ${contaPoupanca.saldo}")

    contaCorrente.sacar(100.0)
    contaPoupanca.sacar(100.0)

    println("saldo após saque corrente: ${contaCorrente.saldo}")
    println("saldo após saque poupança: ${contaPoupanca.saldo}")

    try {
        contaCorrente.transferir(100.0, contaPoupanca)
    } catch (e: SaldoInsuficienteException) {
        println("Falha da transferencia")
        println("Saldo Insuficiente")
    }
    println("saldo corrente após tranferir para poupança: ${contaCorrente.saldo}")
    println("saldo poupança após receber transferência: ${contaPoupanca.saldo}")

    contaPoupanca.transferir(200.0, contaCorrente)

    println("saldo poupança após tranferir para corrente: ${contaPoupanca.saldo}")
    println("saldo corrente após receber transferência: ${contaCorrente.saldo}")

    println("Endereco do titualar: ${contaCorrente.titular.endereco.logradouro}")

    println("Total de contas criadas: ${Conta.total}")
}
