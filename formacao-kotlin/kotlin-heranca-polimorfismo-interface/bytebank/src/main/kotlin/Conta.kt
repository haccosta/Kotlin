open class Conta (val titular : String, val numeroConta: Int) {
        var saldo : Double = 0.0
        protected set

    fun deposita(valorDeposito: Double) {
        this.saldo += valorDeposito;
    }

    open fun saca(valor: Double) {
        if (saldo >= valor) {
            saldo -= valor
        }
    }

    fun transfere(valor: Double, destino: Conta): Boolean {
        if (saldo >= valor) {
            saldo -= valor
            destino.saldo += valor
            return true
        }
        return false
    }
}