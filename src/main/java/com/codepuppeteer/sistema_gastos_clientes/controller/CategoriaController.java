package com.codepuppeteer.sistema_gastos_clientes.controller;

import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.CategoriaResponse;
import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.CategoriaSave;
import com.codepuppeteer.sistema_gastos_clientes.dto.categoria.CategoriaUpdate;
import com.codepuppeteer.sistema_gastos_clientes.service.CategoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String listarPorCliente(@PathVariable Long clienteId, Model model) {
        List<CategoriaResponse> categorias = categoriaService.listarPorCliente(clienteId);
        model.addAttribute("categorias", categorias);
        model.addAttribute("clienteId", clienteId);
        return "cliente/listado_categorias";
    }

    @GetMapping("/cliente/{clienteId}/nueva")
    @PreAuthorize("hasRole('CLIENTE')")
    public String nuevaCategoriaForm(@PathVariable Long clienteId, Model model) {
        model.addAttribute("categoria", new CategoriaSave(null, clienteId));
        return "cliente/nueva_categoria";
    }

    @PostMapping("/cliente/{clienteId}/nueva")
    @PreAuthorize("hasRole('CLIENTE')")
    public String crearCategoria(@ModelAttribute CategoriaSave categoria) {
        categoriaService.crear(categoria);
        return "redirect:/categorias/cliente/" + categoria.clienteId();
    }

    @GetMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String editarCategoriaForm(@PathVariable Long id, Model model) {
        CategoriaResponse categoria = categoriaService.obtenerPorId(id);
        model.addAttribute("categoria", categoria);
        return "cliente/editar_categoria";
    }

    @PostMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String editarCategoriaGuardar(@PathVariable Long id, @ModelAttribute CategoriaUpdate categoria) {
        categoriaService.actualizar(id, categoria);
        return "redirect:/categorias/cliente/" + categoria.clienteId();
    }

    @PostMapping("/{id}/eliminar")
    @PreAuthorize("hasAnyRole('CLIENTE', 'CONTADOR')")
    public String eliminarCategoria(@PathVariable Long id, @RequestParam Long clienteId) {
        categoriaService.eliminar(id);
        return "redirect:/categorias/cliente/" + clienteId;
    }
}
