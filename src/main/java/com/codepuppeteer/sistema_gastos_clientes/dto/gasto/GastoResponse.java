package com.codepuppeteer.sistema_gastos_clientes.dto.gasto;

import com.codepuppeteer.sistema_gastos_clientes.entity.Gasto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GastoResponse(Long id, BigDecimal monto, LocalDate fecha, String descripcion, Long categoriaId) {
    public GastoResponse(Gasto gasto) {
        this(gasto.getId(), gasto.getMonto(), gasto.getFecha(), gasto.getDescripcion(),
                gasto.getCategoria() != null ? gasto.getCategoria().getId() : null);
    }
}
