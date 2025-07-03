package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.ClienteResponse;
import java.util.List;

public interface ContadorService {
    List<ClienteResponse> buscarClientesPorNombre(String nombre);
}
