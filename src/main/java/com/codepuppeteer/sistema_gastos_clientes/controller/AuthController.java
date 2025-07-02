package com.codepuppeteer.sistema_gastos_clientes.controller;

import com.codepuppeteer.sistema_gastos_clientes.dto.usuario.UsuarioSave;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;
import com.codepuppeteer.sistema_gastos_clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    private static final String CLAVE_CONTADOR = "9029";

    @GetMapping("/login")
    public String mostrarLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/auth/redirigir";
        }
        return "auth/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioSave(null, null, "CLIENTE"));
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute UsuarioSave usuario, @RequestParam(required = false) String clave, Model model) {

        if ("CONTADOR".equals(usuario.rol()) && !CLAVE_CONTADOR.equals(clave)) {
            model.addAttribute("error", "Clave de autorización incorrecta para CONTADOR");
            return "auth/registro";
        }

        usuarioService.crear(usuario);
        return "redirect:/auth/login?exito";
    }

    @GetMapping("/redirigir")
    public String redirigirSegunRol() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Usuario usuario = usuarioService.obtenerPorUsername(auth.getName());
            if (usuario.getRol() == Usuario.Rol.CLIENTE) {
                return "redirect:/cliente/dashboard";
            } else if (usuario.getRol() == Usuario.Rol.CONTADOR) {
                return "redirect:/contador/clientes";
            }
        }
        return "redirect:/auth/login";
    }
}
