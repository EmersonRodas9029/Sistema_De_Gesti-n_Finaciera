CREATE TABLE auditoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           usuario_id BIGINT,
                           descripcion TEXT NOT NULL,
                           fecha_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL
);
