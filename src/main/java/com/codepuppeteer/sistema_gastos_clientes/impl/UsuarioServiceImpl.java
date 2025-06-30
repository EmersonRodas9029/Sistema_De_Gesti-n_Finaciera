package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.usuario.*;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;
import com.codepuppeteer.sistema_gastos_clientes.repository.UsuarioRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponse crear(UsuarioSave dto) {
        Usuario usuario = Usuario.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .rol(Usuario.Rol.valueOf(dto.rol()))
                .activo(true)
                .build();

        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponse actualizar(Long id, UsuarioUpdate dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setUsername(dto.username());
        if (dto.password() != null && !dto.password().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.password()));
        }
        usuario.setRol(Usuario.Rol.valueOf(dto.rol()));
        usuario.setActivo(dto.activo());

        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UsuarioResponse(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
