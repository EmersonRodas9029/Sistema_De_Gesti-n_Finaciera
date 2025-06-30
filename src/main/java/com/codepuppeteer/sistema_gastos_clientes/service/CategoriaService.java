package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.CategoriaResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.CategoriaSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.CategoriaUpdate;

import java.util.List;

public interface CategoriaService {

    CategoriaResponse crear(CategoriaSave dto);

    CategoriaResponse actualizar(Long id, CategoriaUpdate dto);

    List<CategoriaResponse> listarPorCliente(Long clienteId);

    CategoriaResponse obtenerPorId(Long id);

    void eliminar(Long id);
}
