package com.codepuppeteer.sistema_gastos_clientes.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaSave(
        @NotBlank String nombre,
        @NotNull Long clienteId
) {
}
