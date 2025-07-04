package com.codepuppeteer.sistema_gastos_clientes.controller;

import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.GastoResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.GastoSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.gasto.GastoUpdate;
import com.codepuppeteer.sistema_gastos_clientes.service.GastoService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gastos")
@RequiredArgsConstructor
public class GastoController {

    private final GastoService gastoService;

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String listarPorCliente(@PathVariable Long clienteId, Model model) {
        List<GastoResponse> gastos = gastoService.listarPorCliente(clienteId);
        model.addAttribute("gastos", gastos);
        model.addAttribute("clienteId", clienteId);
        return "cliente/listado_gastos";
    }

    @GetMapping("/cliente/{clienteId}/nuevo")
    @PreAuthorize("hasRole('CLIENTE')")
    public String nuevoGastoForm(@PathVariable Long clienteId, Model model) {
        model.addAttribute("gasto", new GastoSave(null, null, null, null, clienteId));
        return "cliente/nuevo_gasto";
    }

    @PostMapping("/cliente/{clienteId}/nuevo")
    @PreAuthorize("hasRole('CLIENTE')")
    public String crearGasto(@ModelAttribute GastoSave gasto) {
        gastoService.crear(gasto);
        return "redirect:/gastos/cliente/" + gasto.clienteId();
    }

    @GetMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String editarGastoForm(@PathVariable Long id, Model model) {
        GastoResponse gasto = gastoService.obtenerPorId(id);
        model.addAttribute("gasto", gasto);
        return "cliente/editar_gasto";
    }

    @PostMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String editarGastoGuardar(@PathVariable Long id, @ModelAttribute GastoUpdate gasto) {
        gastoService.actualizar(id, gasto);
        return "redirect:/gastos/cliente/" + gasto.clienteId();
    }

    @PostMapping("/{id}/eliminar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String eliminarGasto(@PathVariable Long id, @RequestParam Long clienteId) {
        gastoService.eliminar(id);
        return "redirect:/gastos/cliente/" + clienteId;
    }
}
