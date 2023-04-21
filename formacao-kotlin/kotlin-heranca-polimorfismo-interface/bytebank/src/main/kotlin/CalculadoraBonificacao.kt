class CalculadoraBonificacao {
    var totalBonificacao : Double = 0.00
        private set

    fun registra(funcionario: Funcionario){
        totalBonificacao += funcionario.bonificacao
    }

   /* fun registra(gerente: Gerente){
        totalBonificacao += gerente.bonificacao
    }
    fun registra(diretor: Diretor){
        totalBonificacao += diretor.bonificacao
    }*/
}