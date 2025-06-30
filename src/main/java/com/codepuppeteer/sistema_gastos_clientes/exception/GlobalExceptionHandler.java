package com.codepuppeteer.sistema_gastos_clientes.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public String manejarRecursoNoEncontrado(RecursoNoEncontradoException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(OperacionNoPermitidaException.class)
    public String manejarOperacionNoPermitida(OperacionNoPermitidaException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/403";
    }

    @ExceptionHandler(Exception.class)
    public String manejarErroresGenerales(Exception ex, Model model) {
        model.addAttribute("error", "Ocurrió un error inesperado");
        return "error/500";
    }
}
