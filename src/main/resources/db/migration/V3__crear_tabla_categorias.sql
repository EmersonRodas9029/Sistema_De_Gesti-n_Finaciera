CREATE TABLE categorias (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            cliente_id BIGINT NOT NULL,
                            nombre VARCHAR(255) NOT NULL,
                            FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);
