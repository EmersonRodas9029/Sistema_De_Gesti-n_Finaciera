package com.codepuppeteer.sistema_gastos_clientes.dto.ingreso;

import com.codepuppeteer.sistema_gastos_clientes.entity.Ingreso;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IngresoResponse(Long id, BigDecimal monto, LocalDate fecha, String descripcion, String tipo) {
    public IngresoResponse(Ingreso ingreso) {
        this(ingreso.getId(), ingreso.getMonto(), ingreso.getFecha(), ingreso.getDescripcion(), ingreso.getTipo().name());
    }
}
