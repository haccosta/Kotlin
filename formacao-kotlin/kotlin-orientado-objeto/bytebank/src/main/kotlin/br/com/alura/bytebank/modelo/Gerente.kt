package br.com.alura.bytebank.modelo

class Gerente(
    nome: String,
    salario: Double,
    cpf: String,
   private val senha: Int
): Funcionario(nome= nome, salario = salario, cpf = cpf)
, Autenticavel {

    override val bonificar: Double
        get() {
            return + this.salario;
        }

    override fun autenticar(senha: Int):Boolean{
        if(this.senha == senha){
            return true
        }
        return false;
    }

}