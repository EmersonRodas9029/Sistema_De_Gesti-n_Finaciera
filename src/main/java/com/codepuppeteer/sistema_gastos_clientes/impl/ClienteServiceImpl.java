package com.codepuppeteer.sistema_gastos_clientes.impl;

import com.codepuppeteer.sistema_gastos_clientes.dto.cliente.*;
import com.codepuppeteer.sistema_gastos_clientes.dto.resumen.GraficaFinanciera;
import com.codepuppeteer.sistema_gastos_clientes.dto.resumen.ResumenMensual;
import com.codepuppeteer.sistema_gastos_clientes.entity.Cliente;
import com.codepuppeteer.sistema_gastos_clientes.entity.Usuario;
import com.codepuppeteer.sistema_gastos_clientes.exception.RecursoNoEncontradoException;
import com.codepuppeteer.sistema_gastos_clientes.repository.ClienteRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.GastoRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.IngresoRepository;
import com.codepuppeteer.sistema_gastos_clientes.repository.UsuarioRepository;
import com.codepuppeteer.sistema_gastos_clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final GastoRepository gastoRepository;
    private final IngresoRepository ingresoRepository;

    @Override
    public ClienteResponse crear(ClienteSave dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cliente cliente = Cliente.builder()
                .nombreCompleto(dto.nombreCompleto())
                .usuario(usuario)
                .build();

        clienteRepository.save(cliente);
        return new ClienteResponse(cliente);
    }

    @Override
    public ClienteResponse actualizar(Long id, ClienteUpdate dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombreCompleto(dto.nombreCompleto());
        clienteRepository.save(cliente);

        return new ClienteResponse(cliente);
    }

    @Override
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return new ClienteResponse(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ResumenMensual obtenerResumenMensual(Long usuarioId) {
        Cliente cliente = clienteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        BigDecimal totalIngresos = ingresoRepository.sumarIngresosPorCliente(cliente.getId());
        BigDecimal totalGastos = gastoRepository.sumarGastosPorCliente(cliente.getId());
        BigDecimal saldo = totalIngresos.subtract(totalGastos);

        String estado = saldo.compareTo(BigDecimal.ZERO) >= 0 ? "Ahorro" : "Pérdida";

        return new ResumenMensual(totalIngresos, totalGastos, saldo, estado);
    }

    @Override
    public GraficaFinanciera obtenerGrafica(Long usuarioId) {
        Cliente cliente = clienteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        List<Object[]> ingresosPorMesRaw = ingresoRepository.obtenerIngresosPorMes(cliente.getId());
        List<Object[]> gastosPorMesRaw = gastoRepository.obtenerGastosPorMes(cliente.getId());

        List<GraficaFinanciera.MesMonto> ingresosPorMes = ingresosPorMesRaw.stream()
                .map(arr -> new GraficaFinanciera.MesMonto(
                        (String) arr[0],
                        (BigDecimal) arr[1]
                ))
                .collect(Collectors.toList());

        List<GraficaFinanciera.MesMonto> gastosPorMes = gastosPorMesRaw.stream()
                .map(arr -> new GraficaFinanciera.MesMonto(
                        (String) arr[0],
                        (BigDecimal) arr[1]
                ))
                .collect(Collectors.toList());

        return new GraficaFinanciera(ingresosPorMes, gastosPorMes);
    }

}
