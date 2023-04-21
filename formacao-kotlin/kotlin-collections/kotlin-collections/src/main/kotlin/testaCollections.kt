public fun testaCollections() {
    val listaNomes: Collection<String> = listOf("Nome 1", "Nome 2", "Nome 3")

    for (nome in listaNomes) {
        println(nome)
    }

    println("Nome 1 exista na lista? ${listaNomes.contains("Nome 1")}")
    println("Tamanho da lista de nomes: ${listaNomes.size}")

    println(listaNomes)

    val bancoDeNomes = BancoDeNomes()

    bancoDeNomes.salvar("Nome 1")
    bancoDeNomes.salvar("Nome 2")

    println(bancoDeNomes.nomes)

    println(BancoDeNomes().nomes)
}