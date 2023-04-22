package com.costa.handerson.resfulcomkotlin.controller

import com.costa.handerson.resfulcomkotlin.services.TopicoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("relatorios")
class RelatorioController (
    private val topicoService : TopicoService
) {

    @GetMapping()
    fun gerarRelatoro(model : Model):String{
        model.addAttribute("topicosPorCategorias",topicoService.gerarRelatorio())
        return "relatorio"

    }
}