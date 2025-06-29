package com.codepuppeteer.sistema_gastos_clientes.dto.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteUpdate(
        @NotBlank String nombreCompleto
) {
}
