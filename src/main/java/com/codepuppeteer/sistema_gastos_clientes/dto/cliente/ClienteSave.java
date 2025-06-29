package com.codepuppeteer.sistema_gastos_clientes.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteSave(
        @NotBlank String nombreCompleto,
        @NotNull Long usuarioId
) {
}
