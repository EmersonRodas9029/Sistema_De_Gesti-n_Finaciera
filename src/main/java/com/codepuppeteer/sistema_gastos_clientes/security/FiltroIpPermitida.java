package com.codepuppeteer.sistema_gastos_clientes.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroIpPermitida implements Filter {

    private final String ipPermitida;

    public FiltroIpPermitida(String ipPermitida) {
        this.ipPermitida = ipPermitida;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ipCliente = httpRequest.getRemoteAddr();

        if (!ipCliente.equals(ipPermitida)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso restringido por IP");
            return;
        }

        chain.doFilter(request, response);
    }
}
