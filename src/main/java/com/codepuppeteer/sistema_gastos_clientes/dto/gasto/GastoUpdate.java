package com.codepuppeteer.sistema_gastos_clientes.dto.gasto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GastoUpdate(
        @NotNull BigDecimal monto,
        @NotNull LocalDate fecha,
        String descripcion,
        Long categoriaId,
        @NotNull Long clienteId
) {}

