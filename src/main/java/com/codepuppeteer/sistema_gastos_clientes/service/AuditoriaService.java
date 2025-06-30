package com.codepuppeteer.sistema_gastos_clientes.service;

import com.codepuppeteer.sistema_gastos_clientes.entity.Auditoria;

import java.util.List;

public interface AuditoriaService {

    void registrarEvento(Long usuarioId, String descripcion);

    List<Auditoria> listarTodo();
}
