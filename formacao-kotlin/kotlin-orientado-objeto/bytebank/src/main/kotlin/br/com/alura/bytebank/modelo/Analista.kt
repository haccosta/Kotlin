package br.com.alura.bytebank.modelo

class Analista(
    nome: String,
    salario: Double,
    cpf: String,
    val plr: Double,
    val senha: Int
): Funcionario(nome= nome, salario = salario, cpf = cpf) {

    override val bonificar: Double
        get() {
            return this.salario + plr;
        }

    fun autentica(senha: Int): Boolean {
        if (this.senha == senha) {
            return true
        }
        return false
    }
}