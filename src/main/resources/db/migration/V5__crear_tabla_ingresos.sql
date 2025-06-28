CREATE TABLE ingresos (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          cliente_id BIGINT NOT NULL,
                          monto DECIMAL(10,2) NOT NULL,
                          fecha DATE NOT NULL,
                          tipo ENUM('ESTABLE', 'VOLATIL') NOT NULL,
                          descripcion TEXT,
                          fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);
