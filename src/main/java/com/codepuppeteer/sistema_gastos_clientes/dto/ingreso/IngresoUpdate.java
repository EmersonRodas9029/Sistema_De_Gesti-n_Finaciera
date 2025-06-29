package com.codepuppeteer.sistema_gastos_clientes.dto.ingreso;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IngresoUpdate(
        @NotNull BigDecimal monto,
        @NotNull LocalDate fecha,
        String descripcion,
        @NotNull String tipo
) {
}
