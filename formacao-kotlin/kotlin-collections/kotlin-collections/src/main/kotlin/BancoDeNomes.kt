class BancoDeNomes {
    val nomes: Collection<String> get() = dados.toList()

    fun salvar(nome:String){
        Companion.dados.add(nome)
    }

    companion object {
        private val dados = mutableListOf<String>()
    }


}