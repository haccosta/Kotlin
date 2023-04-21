fun main(){
    val teste : (valor:Int, valor2: Int) -> Unit = :: testeFuncao
    println(teste(1,1))
    val minhaClasse: () -> Unit = Teste()
    println(minhaClasse)

    val funcaoLambda : () -> Unit = {
        println("Funcao Lambda")
    }

    val funcaoAnonima = fun() {
        println("Funcao Anonima")
    }

    println(funcaoLambda())
    println(funcaoAnonima())

    var en


}

fun testeFuncao(valor:Int, valor2:Int){
    println ("executa teste")
}

class Teste : () -> Unit{
    override fun invoke() {
        println("eecuta invoke do teste")
    }
}