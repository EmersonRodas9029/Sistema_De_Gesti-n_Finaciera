package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.*;
import com.codepuppeteer.sistema_gastos_clientes.entity.Cliente;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;
import com.codepuppeteer.sistema_gastos_clientes.repository.ClienteRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.UsuarioRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.ClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ClienteResponse crear(ClienteSave dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cliente cliente = Cliente.builder()
                .nombreCompleto(dto.nombreCompleto())
                .usuario(usuario)
                .build();

        clienteRepository.save(cliente);
        return new ClienteResponse(cliente);
    }

    @Override
    public ClienteResponse actualizar(Long id, ClienteUpdate dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombreCompleto(dto.nombreCompleto());
        clienteRepository.save(cliente);

        return new ClienteResponse(cliente);
    }

    @Override
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return new ClienteResponse(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
