package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.ClienteResponse;
import com.codepuppeteer.sistema_gastos_clientes.repository.ClienteRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.ContadorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContadorServiceImpl implements ContadorService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponse> buscarClientesPorNombre(String nombre) {
        return clienteRepository.findByNombreCompletoContainingIgnoreCase(nombre).stream()
                .map(ClienteResponse::new)
                .collect(Collectors.toList());
    }
}
