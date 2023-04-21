package br.com.alura.bytebank.modelo

class Calculadora {
    var total : Double = 0.00
        private set

    fun registra(funcionario: Funcionario){
        this.total += funcionario.bonificar
    }
}
