package com.codepuppeteer.sistema_gastos_clientes.controller;

import com.codepuppeteer.sistema_gastos_clientes.dto.resumen.ResumenMensual;
import com.codepuppeteer.sistema_gastos_clientes.dto.resumen.GraficaFinanciera;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;
import com.codepuppeteer.sistema_gastos_clientes.service.ClienteService;
import com.codepuppeteer.sistema_gastos_clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final UsuarioService usuarioService;
    private final ClienteService clienteService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {

        Usuario usuario = usuarioService.obtenerPorUsername(auth.getName());

        ResumenMensual resumen = clienteService.obtenerResumenMensual(usuario.getId());
        GraficaFinanciera grafica = clienteService.obtenerGrafica(usuario.getId());

        model.addAttribute("resumen", resumen);
        model.addAttribute("grafica", grafica);

        return "cliente/dashboard_cliente";
    }
}
