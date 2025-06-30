package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.entity.Auditoria;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;
import com.codepuppeteer.sistema_gastos_clientes.repository.AuditoriaRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.UsuarioRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.AuditoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void registrarEvento(Long usuarioId, String descripcion) {
        Usuario usuario = null;
        if (usuarioId != null) {
            usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }

        Auditoria auditoria = Auditoria.builder()
                .usuario(usuario)
                .descripcion(descripcion)
                .build();

        auditoriaRepository.save(auditoria);
    }

    @Override
    public List<Auditoria> listarTodo() {
        return auditoriaRepository.findAll();
    }
}
