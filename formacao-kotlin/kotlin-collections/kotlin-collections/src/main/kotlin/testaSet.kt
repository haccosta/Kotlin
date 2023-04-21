fun testaSet() {
    val alunosFizeramCursoAndroid: Set<String> = setOf<String>("Aluno 1", "Aluno 2", "Aluno 3")
    val alunosFizeramCursoKotlin: Set<String> = setOf<String>("Aluno 1", "Aluno 5")
    val alunosGeral = mutableSetOf<String>()

    alunosGeral.addAll(alunosFizeramCursoAndroid)
    alunosGeral.addAll(alunosFizeramCursoKotlin)

    alunosGeral.add("Aluno 2");

    //println(alunosGeral)
    println(alunosGeral.distinct())
}
