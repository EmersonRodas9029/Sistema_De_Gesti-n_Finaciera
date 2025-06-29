package com.codepuppeteer.sistema_gastos_clientes.dto.usuario;

import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;

public record UsuarioResponse(Long id, String username, String rol, Boolean activo) {
    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getRol().name(), usuario.getActivo());
    }
}
