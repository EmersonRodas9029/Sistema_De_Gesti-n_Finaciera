package com.codepuppeteer.sistema_gastos_clientes.dto.resumen;

import java.math.BigDecimal;

public record ResumenMensual(
        BigDecimal totalIngresos,
        BigDecimal totalGatos,
        BigDecimal saldo,
        String estado
) {}


