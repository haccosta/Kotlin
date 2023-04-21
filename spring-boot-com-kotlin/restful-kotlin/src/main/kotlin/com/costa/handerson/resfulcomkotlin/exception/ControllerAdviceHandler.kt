package com.costa.handerson.resfulcomkotlin.exception

import com.costa.handerson.resfulcomkotlin.dto.RespostaErro
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ControllerAdviceHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(exception: NotFoundException, request: HttpServletRequest): RespostaErro {

        return RespostaErro(
            status = HttpStatus.NOT_FOUND.value(),
            erro = HttpStatus.NOT_FOUND.name,
            mensagem = exception.message,
            path = request.servletPath

        )

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerValidationError(exception: MethodArgumentNotValidException, request: HttpServletRequest): RespostaErro {

        val errosMensagem = HashMap<String, String?>();

        exception.bindingResult.fieldErrors.forEach { erro ->
            errosMensagem.put(erro.field, erro.defaultMessage)
        }

        return RespostaErro(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = HttpStatus.BAD_REQUEST.name,
            mensagem = errosMensagem.toString(),
            path = request.servletPath

        )

    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerServerError(exception: Exception, request: HttpServletRequest): RespostaErro {

        return RespostaErro(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            erro = HttpStatus.INTERNAL_SERVER_ERROR.name,
            mensagem = exception.message,
            path = request.servletPath

        )

    }
}