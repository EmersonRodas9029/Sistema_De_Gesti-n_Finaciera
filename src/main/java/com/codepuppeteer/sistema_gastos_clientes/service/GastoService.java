package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.GastoResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.GastoSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.GastoUpdate;

import java.util.List;

public interface GastoService {

    GastoResponse crear(GastoSave dto);

    GastoResponse actualizar(Long id, GastoUpdate dto);

    List<GastoResponse> listarPorCliente(Long clienteId);

    GastoResponse obtenerPorId(Long id);

    void eliminar(Long id);
}
