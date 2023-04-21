package br.com.alura.bytebank.modelo

class Diretor(
    nome: String,
    salario: Double,
    cpf: String,
    val plr: Double,
    private val senha: Int
): Funcionario(nome= nome, salario = salario, cpf = cpf)
, Autenticavel {

    override val bonificar: Double
        get() {
            return this.salario + plr;
        }

    override fun autenticar(senha: Int):Boolean{
        if(this.senha == senha){
            return true
        }
        return false;
    }

}