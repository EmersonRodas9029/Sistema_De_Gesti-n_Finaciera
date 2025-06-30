package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.*;
import com.codepuppeteer.sistema_gastos_clientes.entity.Categoria;
import com.codepuppeteer.sistema_gastos_clientes.entity.Cliente;
import com.codepuppeteer.sistema_gastos_clientes.repository.CategoriaRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.ClienteRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.CategoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public CategoriaResponse crear(CategoriaSave dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Categoria categoria = Categoria.builder()
                .nombre(dto.nombre())
                .cliente(cliente)
                .build();

        categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria);
    }

    @Override
    public CategoriaResponse actualizar(Long id, CategoriaUpdate dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoria.setNombre(dto.nombre());
        categoriaRepository.save(categoria);

        return new CategoriaResponse(categoria);
    }

    @Override
    public List<CategoriaResponse> listarPorCliente(Long clienteId) {
        return categoriaRepository.findByClienteId(clienteId).stream()
                .map(CategoriaResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponse obtenerPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return new CategoriaResponse(categoria);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
