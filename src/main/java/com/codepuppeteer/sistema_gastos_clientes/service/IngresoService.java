package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.IngresoResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.IngresoSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.IngresoUpdate;

import java.util.List;

public interface IngresoService {

    IngresoResponse crear(IngresoSave dto);

    IngresoResponse actualizar(Long id, IngresoUpdate dto);

    List<IngresoResponse> listarPorCliente(Long clienteId);

    IngresoResponse obtenerPorId(Long id);

    void eliminar(Long id);
}
