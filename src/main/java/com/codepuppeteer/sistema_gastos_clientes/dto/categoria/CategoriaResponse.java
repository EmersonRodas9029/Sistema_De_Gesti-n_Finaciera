package com.codepuppeteer.sistema_gastos_clientes.dto.categoria;

import com.codepuppeteer.sistema_gastos_clientes.entity.Categoria;

public record CategoriaResponse(Long id, String nombre, Long clienteId) {
    public CategoriaResponse(Categoria categoria) {
        this(categoria.getId(), categoria.getNombre(), categoria.getCliente().getId());
    }
}
