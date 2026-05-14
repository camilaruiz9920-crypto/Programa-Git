CREATE DATABASE IF NOT EXISTS tienda_adidas;
USE tienda_adidas;

CREATE TABLE producto (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    categoria VARCHAR(50),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insertar datos de ejemplo
INSERT INTO producto (nombre, descripcion, precio, stock, categoria) VALUES
('Ultraboost 22', 'Zapatillas running con tecnología Boost', 450000, 15, 'Calzado'),
('Camiseta Entrenamiento', 'Camiseta transpirable para gym', 120000, 30, 'Ropa'),
('Gorra Adidas', 'Gorra deportiva unisex', 89000, 50, 'Accesorios');
