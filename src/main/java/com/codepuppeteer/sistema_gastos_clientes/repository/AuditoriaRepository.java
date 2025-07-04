package com.codepuppeteer.sistema_gastos_clientes.repository;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import com.codepuppeteer.sistema_gastos_clientes.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    // Buscar auditorías por usuario específico
    List<Auditoria> findByUsuarioId(Long usuarioId);

    // Buscar auditorías por rango de fechas
    List<Auditoria> findByFechaEventoBetween(LocalDateTime inicio, LocalDateTime fin);

    // Buscar auditorías que contengan un texto en la descripción
    List<Auditoria> findByDescripcionContainingIgnoreCase(String texto);

    // Consulta JPQL personalizada: buscar auditorías recientes limitadas a N resultados
    @Query("SELECT a FROM Auditoria a ORDER BY a.fechaEvento DESC")
    List<Auditoria> findTop10ByOrderByFechaEventoDesc();

    // Consulta nativa para filtros más complejos, si es necesario (opcional)
    // @Query(value = "SELECT * FROM auditoria WHERE descripcion LIKE %:texto% ORDER BY fecha_evento DESC LIMIT 10", nativeQuery = true)
    // List<Auditoria> buscarUltimasAuditoriasPorDescripcion(@Param("texto") String texto);
}
