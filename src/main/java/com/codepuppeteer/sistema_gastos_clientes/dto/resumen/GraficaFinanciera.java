package com.codepuppeteer.sistema_gastos_clientes.dto.resumen;

import java.math.BigDecimal;
import java.util.List;

public record GraficaFinanciera(
        List<MesMonto> ingresos,
        List<MesMonto> gastos
) {

    public record MesMonto(
            String mes,
            BigDecimal monto
    ) {}
}
