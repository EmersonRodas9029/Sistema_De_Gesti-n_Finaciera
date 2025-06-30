package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.*;
import com.codepuppeteer.sistema_gastos_clientes.entity.Cliente;
import com.codepuppeteer.sistema_gastos_clientes.entity.Ingreso;
import com.codepuppeteer.sistema_gastos_clientes.repository.ClienteRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.IngresoRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.IngresoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRepository ingresoRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public IngresoResponse crear(IngresoSave dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Ingreso ingreso = Ingreso.builder()
                .cliente(cliente)
                .monto(dto.monto())
                .fecha(dto.fecha())
                .descripcion(dto.descripcion())
                .tipo(Ingreso.Tipo.valueOf(dto.tipo()))
                .build();

        ingresoRepository.save(ingreso);
        return new IngresoResponse(ingreso);
    }

    @Override
    public IngresoResponse actualizar(Long id, IngresoUpdate dto) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));

        ingreso.setMonto(dto.monto());
        ingreso.setFecha(dto.fecha());
        ingreso.setDescripcion(dto.descripcion());
        ingreso.setTipo(Ingreso.Tipo.valueOf(dto.tipo()));

        ingresoRepository.save(ingreso);
        return new IngresoResponse(ingreso);
    }

    @Override
    public List<IngresoResponse> listarPorCliente(Long clienteId) {
        return ingresoRepository.findByClienteId(clienteId).stream()
                .map(IngresoResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public IngresoResponse obtenerPorId(Long id) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));
        return new IngresoResponse(ingreso);
    }

    @Override
    public void eliminar(Long id) {
        ingresoRepository.deleteById(id);
    }
}
