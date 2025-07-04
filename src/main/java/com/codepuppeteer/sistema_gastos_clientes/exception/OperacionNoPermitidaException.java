package com.codepuppeteer.sistema_gastos_clientes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OperacionNoPermitidaException extends RuntimeException {

    public OperacionNoPermitidaException() {
        super("Operación no permitida.");
    }

    public OperacionNoPermitidaException(String mensaje) {
        super(mensaje);
    }

    public OperacionNoPermitidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public OperacionNoPermitidaException(Throwable causa) {
        super(causa);
    }
}
