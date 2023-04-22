package com.costa.handerson.resfulcomkotlin.model

class TopicoTest {
    fun builder() = Topico(
        titulo = "Topico 1",
        mensagem = "Mensagem 1",
        id = 1,
        autor = UsuarioTest().builder(),
        curso = CursoTest().builder()


    )
}