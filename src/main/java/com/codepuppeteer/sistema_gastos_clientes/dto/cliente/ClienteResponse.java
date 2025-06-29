package com.codepuppeteer.sistema_gastos_clientes.dto.cliente;

import com.codepuppeteer.sistema_gastos_clientes.entity.Cliente;

public record ClienteResponse(Long id, String nombreCompleto, Long usuarioId) {
    public ClienteResponse(Cliente cliente) {
        this(cliente.getId(), cliente.getNombreCompleto(), cliente.getUsuario().getId());
    }
}
