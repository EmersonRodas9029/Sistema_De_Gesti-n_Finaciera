package com.codepuppeteer.sistema_gastos_clientes.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaUpdate(
        @NotBlank String nombre,
        @NotBlank Long clienteId
) {}

