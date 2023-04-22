package com.costa.handerson.resfulcomkotlin.services

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
   private val sender: JavaMailSender
) {

    fun enviarEmail(autor: String) {
        val mensagem = SimpleMailMessage()

        mensagem.subject = "[Alura] Resposta Recebida";
        mensagem.text = "Ola, seu topico foi respondido, vamos lá conferir?"
        mensagem.setTo(autor);

        sender.send(mensagem)

    }

}