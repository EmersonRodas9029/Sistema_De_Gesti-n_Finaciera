package com.codepuppeteer.sistema_gastos_clientes.repository;

import com.codepuppeteer.sistema_gastos_clientes.entity.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    List<Ingreso> findByClienteId(Long clienteId);

    @Query("SELECT COALESCE(SUM(i.monto), 0) FROM Ingreso i WHERE i.cliente.id = :clienteId")
    BigDecimal sumarIngresosPorCliente(Long clienteId);

    @Query("SELECT FUNCTION('DATE_FORMAT', i.fecha, '%Y-%m') AS mes, SUM(i.monto) " +
            "FROM Ingreso i WHERE i.cliente.id = :clienteId " +
            "GROUP BY FUNCTION('DATE_FORMAT', i.fecha, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', i.fecha, '%Y-%m')")
    List<Object[]> obtenerIngresosPorMes(Long clienteId);
}
