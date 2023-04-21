fun main(){

    println("Bem Vindo ao Bytebank")

    val funcionario = Funcionario(
        nome="Handerson",
        cpf ="12666666666",
        salario = 1000.00
    )

    println("Nome do funcionario: ${funcionario.nome}")
    println("CPF do funcionario: ${funcionario.cpf}")
    println("Salario do funcionario: ${funcionario.salario}")
    println("Bonificacao do funcionario: ${funcionario.bonificacao}")

    val gerente = Gerente(
        nome="Handerson",
        cpf ="12666666666",
        salario = 2000.00,
        senha = 1234
    )

    println("Nome do gerente: ${gerente.nome}")
    println("CPF do gerente: ${gerente.cpf}")
    println("Salario do gerente: ${gerente.salario}")
    println("Bonificacao do gerente: ${gerente.bonificacao}")
    println("Gerente autenticou? ${gerente.autentica(1234)}")

    val diretor = Diretor(
        nome="Handerson",
        cpf ="12666666666",
        salario = 2000.00,
        senha = 1234,
        plr = 200.00
    )

    println("Nome do diretor: ${diretor.nome}")
    println("CPF do diretor: ${diretor.cpf}")
    println("Salario do diretor: ${diretor.salario}")
    println("Bonificacao do diretor: ${diretor.bonificacao}")
    println("PLR do diretor: ${diretor.plr}")
    println("Diretor autenticou? ${diretor.autentica(1233)}")

    val calculadora = CalculadoraBonificacao()
    calculadora.registra(funcionario)
    calculadora.registra(gerente)
    calculadora.registra(diretor)

    println("O total de bonificacao: ${calculadora.totalBonificacao}")

    val contaCorrente = ContaCorrente(
        titular = "Alex",
        numeroConta = 1000
    )

    val contaPoupanca = ContaPoupanca(
        titular = "Fran",
        numeroConta = 1001
    )

    contaCorrente.deposita(1000.0)
    contaPoupanca.deposita(1000.0)

    println("saldo corrente: ${contaCorrente.saldo}")
    println("saldo poupança: ${contaPoupanca.saldo}")

    contaCorrente.saca(100.0)
    contaPoupanca.saca(100.0)

    println("saldo após saque corrente: ${contaCorrente.saldo}")
    println("saldo após saque poupança: ${contaPoupanca.saldo}")

    contaCorrente.transfere(100.0, contaPoupanca)

    println("saldo corrente após tranferir para poupança: ${contaCorrente.saldo}")
    println("saldo poupança após receber transferência: ${contaPoupanca.saldo}")

    contaPoupanca.transfere(200.0, contaCorrente)

    println("saldo poupança após tranferir para corrente: ${contaPoupanca.saldo}")
    println("saldo corrente após receber transferência: ${contaCorrente.saldo}")


}