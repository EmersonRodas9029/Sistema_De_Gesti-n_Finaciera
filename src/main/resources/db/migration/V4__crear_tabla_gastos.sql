CREATE TABLE gastos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        cliente_id BIGINT NOT NULL,
                        monto DECIMAL(10,2) NOT NULL,
                        fecha DATE NOT NULL,
                        categoria_id BIGINT,
                        descripcion TEXT,
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE,
                        FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE SET NULL
);
