package br.com.alura.bytebank.teste

import br.com.alura.bytebank.modelo.*

fun testeFuncionarios() {
    val funcionario = Analista(nome = "Funcionario 1", cpf = "111.111.111-11", salario=1000.00, plr=100.00, senha=1234)

    println("modelo.Funcionario: ${funcionario.nome}")
    println("Cpf do modelo.Funcionario: ${funcionario.cpf}")
    println("Salario: ${funcionario.salario}")
    println("Bonificacao: ${funcionario.bonificar}")

    val gerente = Gerente(nome = "Gerente 1", salario = 2000.00, cpf = "222.222.222.22", senha = 12345);

    println("modelo.Gerente: ${gerente.nome}")
    println("Cpf do modelo.Gerente: ${gerente.cpf}")
    println("Salario: ${gerente.salario}")
    println("Bonificacao: ${gerente.bonificar}")

    val sistemaInterno = SistemaInterno();
    sistemaInterno.autenticar(gerente, 12345);


    val diretor = Diretor(nome = "modelo.Gerente 1", salario = 2000.00, cpf = "222.222.222.22", plr = 1000.0, senha = 12345);

    println("modelo.Diretor: ${diretor.nome}")
    println("Cpf do modelo.Diretor: ${diretor.cpf}")
    println("Salario: ${diretor.salario}")
    println("PLR: ${diretor.plr}")
    println("Bonificacao: ${diretor.bonificar}")

    sistemaInterno.autenticar(diretor, 12345);

    val analista = Analista(nome = "Analista", cpf = "12345678", salario = 1000.00, plr = 100.00, senha = 1234)

    val calculadora = Calculadora()
    calculadora.registra(funcionario)
    calculadora.registra(gerente)
    calculadora.registra(diretor)
    calculadora.registra(analista)

    println("total de bonificação: ${calculadora.total}")

    val cliente = Cliente(nome="modelo.Cliente", cpf="123456", senha=1234)

    sistemaInterno.autenticar(cliente, 12345);
}
