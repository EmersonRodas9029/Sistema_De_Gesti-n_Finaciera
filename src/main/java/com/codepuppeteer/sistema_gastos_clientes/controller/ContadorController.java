package com.codepuppeteer.sistema_gastos_clientes.controller;

import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.ClienteResponse;
import com.codepuppeteer.sistema_gastos_clientes.service.ContadorService;
import com.codepuppeteer.sistema_gastos_clientes.service.ClienteService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contador")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CONTADOR')")
public class ContadorController {

    private final ClienteService clienteService;
    private final ContadorService contadorService;

    @GetMapping("/clientes")
    public String listarClientes(@RequestParam(value = "nombre", required = false) String nombreFiltro, Model model) {
        List<ClienteResponse> clientes;
        if (nombreFiltro != null && !nombreFiltro.isEmpty()) {
            clientes = contadorService.buscarClientesPorNombre(nombreFiltro);
        } else {
            clientes = clienteService.listar();
        }
        model.addAttribute("clientes", clientes);
        return "contador/listado_clientes";
    }

    @GetMapping("/clientes/{id}")
    public String detalleCliente(@PathVariable Long id, Model model) {
        ClienteResponse cliente = clienteService.obtenerPorId(id);
        model.addAttribute("cliente", cliente);
        return "contador/detalle_cliente";
    }

    @GetMapping("/clientes/{id}/editar")
    public String editarClienteForm(@PathVariable Long id, Model model) {
        ClienteResponse cliente = clienteService.obtenerPorId(id);
        model.addAttribute("cliente", cliente);
        return "contador/editar_cliente";
    }

    @PostMapping("/clientes/{id}/editar")
    public String editarClienteGuardar(@PathVariable Long id, @ModelAttribute("cliente") ClienteResponse clienteDto) {

        return "redirect:/contador/clientes/" + id;
    }

    @GetMapping("/clientes/{id}/reporte")
    public String exportarReportePdf(@PathVariable Long id) {
        return "contador/reporte_pdf";
    }
}
