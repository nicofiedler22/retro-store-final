CREATE TABLE wishlist_items (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    nombre_producto VARCHAR(150) NOT NULL,
    precio_referencia DECIMAL(12,2),
    fecha_agregado DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_usuario_producto UNIQUE (usuario_id, producto_id),
    INDEX idx_wishlist_usuario (usuario_id)
);
