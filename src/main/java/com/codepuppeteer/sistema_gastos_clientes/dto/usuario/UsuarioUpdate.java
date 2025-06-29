package com.codepuppeteer.sistema_gastos_clientes.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdate(
        @NotBlank String username,
        @Size(min = 6) String password,
        @NotBlank String rol,
        Boolean activo
) {
}
