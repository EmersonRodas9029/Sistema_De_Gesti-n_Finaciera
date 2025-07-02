package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.dto.usuario.UsuarioResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.usuario.UsuarioSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.usuario.UsuarioUpdate;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse crear(UsuarioSave dto);

    UsuarioResponse actualizar(Long id, UsuarioUpdate dto);

    List<UsuarioResponse> listar();

    UsuarioResponse obtenerPorId(Long id);

    void eliminar(Long id);

    Usuario obtenerPorUsername(String username);

}
