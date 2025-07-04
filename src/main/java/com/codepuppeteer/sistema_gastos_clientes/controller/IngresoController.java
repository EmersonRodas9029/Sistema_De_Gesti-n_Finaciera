package com.codepuppeteer.sistema_gastos_clientes.controller;

import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.IngresoResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.IngresoSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.ingreso.IngresoUpdate;
import com.codepuppeteer.sistema_gastos_clientes.service.IngresoService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingresos")
@RequiredArgsConstructor
public class IngresoController {

    private final IngresoService ingresoService;

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String listarPorCliente(@PathVariable Long clienteId, Model model) {
        List<IngresoResponse> ingresos = ingresoService.listarPorCliente(clienteId);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("clienteId", clienteId);
        return "cliente/listado_ingresos";
    }

    @GetMapping("/cliente/{clienteId}/nuevo")
    @PreAuthorize("hasRole('CLIENTE')")
    public String nuevoIngresoForm(@PathVariable Long clienteId, Model model) {
        model.addAttribute("ingreso", new IngresoSave(null, null, null, null, clienteId));
        return "cliente/nuevo_ingreso";
    }

    @PostMapping("/cliente/{clienteId}/nuevo")
    @PreAuthorize("hasRole('CLIENTE')")
    public String crearIngreso(@ModelAttribute IngresoSave ingreso) {
        ingresoService.crear(ingreso);
        return "redirect:/ingresos/cliente/" + ingreso.clienteId();
    }

    @GetMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String editarIngresoForm(@PathVariable Long id, Model model) {
        IngresoResponse ingreso = ingresoService.obtenerPorId(id);
        model.addAttribute("ingreso", ingreso);
        return "cliente/editar_ingreso";
    }

    @PostMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String editarIngresoGuardar(@PathVariable Long id, @ModelAttribute IngresoUpdate ingreso) {
        ingresoService.actualizar(id, ingreso);
        return "redirect:/ingresos/cliente/" + ingreso.clienteId();
    }

    @PostMapping("/{id}/eliminar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String eliminarIngreso(@PathVariable Long id, @RequestParam Long clienteId) {
        ingresoService.eliminar(id);
        return "redirect:/ingresos/cliente/" + clienteId;
    }
}
