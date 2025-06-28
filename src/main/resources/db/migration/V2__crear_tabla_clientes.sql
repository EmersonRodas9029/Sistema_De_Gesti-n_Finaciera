CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          usuario_id BIGINT NOT NULL,
                          nombre_completo VARCHAR(255) NOT NULL,
                          FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
