package com.codepuppeteer.sistema_gastos_clientes.repository;

import com.codepuppeteer.sistema_gastos_clientes.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    List<Gasto> findByClienteId(Long clienteId);

    @Query("SELECT COALESCE(SUM(g.monto), 0) FROM Gasto g WHERE g.cliente.id = :clienteId")
    BigDecimal sumarGastosPorCliente(Long clienteId);

    @Query("SELECT FUNCTION('DATE_FORMAT', g.fecha, '%Y-%m') AS mes, SUM(g.monto) " +
            "FROM Gasto g WHERE g.cliente.id = :clienteId " +
            "GROUP BY FUNCTION('DATE_FORMAT', g.fecha, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', g.fecha, '%Y-%m')")
    List<Object[]> obtenerGastosPorMes(Long clienteId);
}
