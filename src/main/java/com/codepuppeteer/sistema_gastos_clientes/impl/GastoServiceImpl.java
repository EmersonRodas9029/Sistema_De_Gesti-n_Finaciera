package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.*;
import com.codepuppeteer.sistema_gastos_clientes.entity.Categoria;
import com.codepuppeteer.sistema_gastos_clientes.entity.Cliente;
import com.codepuppeteer.sistema_gastos_clientes.entity.Gasto;
import com.codepuppeteer.sistema_gastos_clientes.repository.CategoriaRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.ClienteRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.GastoRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.GastoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GastoServiceImpl implements GastoService {

    private final GastoRepository gastoRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public GastoResponse crear(GastoSave dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Categoria categoria = null;
        if (dto.categoriaId() != null) {
            categoria = categoriaRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        }

        Gasto gasto = Gasto.builder()
                .cliente(cliente)
                .monto(dto.monto())
                .fecha(dto.fecha())
                .descripcion(dto.descripcion())
                .categoria(categoria)
                .build();

        gastoRepository.save(gasto);
        return new GastoResponse(gasto);
    }

    @Override
    public GastoResponse actualizar(Long id, GastoUpdate dto) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

        gasto.setMonto(dto.monto());
        gasto.setFecha(dto.fecha());
        gasto.setDescripcion(dto.descripcion());

        if (dto.categoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
            gasto.setCategoria(categoria);
        } else {
            gasto.setCategoria(null);
        }

        gastoRepository.save(gasto);
        return new GastoResponse(gasto);
    }

    @Override
    public List<GastoResponse> listarPorCliente(Long clienteId) {
        return gastoRepository.findByClienteId(clienteId).stream()
                .map(GastoResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public GastoResponse obtenerPorId(Long id) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
        return new GastoResponse(gasto);
    }

    @Override
    public void eliminar(Long id) {
        gastoRepository.deleteById(id);
    }
}
