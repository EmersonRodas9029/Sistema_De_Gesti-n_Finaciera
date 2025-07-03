package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.ClienteResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.ClienteSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.ClienteUpdate;
import com.codepuppeteer.sistema_gastos_clientes.dto.resumen.GraficaFinanciera;
import com.codepuppeteer.sistema_gastos_clientes.dto.resumen.ResumenMensual;

import java.util.List;

public interface ClienteService {

    ClienteResponse crear(ClienteSave dto);

    ClienteResponse actualizar(Long id, ClienteUpdate dto);

    List<ClienteResponse> listar();

    ClienteResponse obtenerPorId(Long id);

    void eliminar(Long id);

    ResumenMensual obtenerResumenMensual(Long usuarioId);

    GraficaFinanciera obtenerGrafica(Long usuarioId);
}
