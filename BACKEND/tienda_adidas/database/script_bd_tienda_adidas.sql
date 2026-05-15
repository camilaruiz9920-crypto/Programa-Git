-- =====================================================
-- Evidencia GA6-220501096-AA2-EV03. Script bases de datos del proyecto
-- ANALISIS Y DESARROLLO DE SOFTWARE (3186633)
-- REALIZADO POR:
-- ARIAGNA CAMILA CONDE RUIZ
-- JOSÉ GREGORIO NARVÁEZ ALARCÓN
-- Instructor: Luis Manuel Cabrales Valdés
-- SENA - 2026
-- =====================================================

-- =====================================================
-- 1. ELIMINAR BASE DE DATOS ANTERIOR (SI EXISTE)
-- =====================================================
DROP DATABASE IF EXISTS `tienda_adidas`;
CREATE DATABASE `tienda_adidas` 
    DEFAULT CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE `tienda_adidas`;

-- =====================================================
-- 2. CREACIÓN DE TABLAS CON TODAS SUS RESTRICCIONES
-- =====================================================

-- -----------------------------------------------------
-- Tabla ROL
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ROL` (
    `id_rol` INT NOT NULL AUTO_INCREMENT,
    `nombre_rol` VARCHAR(50) NOT NULL,
    `descripcion` VARCHAR(255) NULL,
    PRIMARY KEY (`id_rol`),
    UNIQUE INDEX `nombre_rol_UNIQUE` (`nombre_rol` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla USUARIO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `USUARIO` (
    `id_usuario` INT NOT NULL AUTO_INCREMENT,
    `nombre_completo` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `contrasena_hash` VARCHAR(255) NOT NULL,
    `telefono` VARCHAR(20) NULL,
    `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `activo` TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (`id_usuario`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla USUARIO_ROL (relación N:M)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `USUARIO_ROL` (
    `id_usuario` INT NOT NULL,
    `id_rol` INT NOT NULL,
    `fecha_asignacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_usuario`, `id_rol`),
    INDEX `fk_usuario_rol_rol_idx` (`id_rol` ASC),
    CONSTRAINT `fk_usuario_rol_usuario`
        FOREIGN KEY (`id_usuario`)
        REFERENCES `USUARIO` (`id_usuario`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT `fk_usuario_rol_rol`
        FOREIGN KEY (`id_rol`)
        REFERENCES `ROL` (`id_rol`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla DIRECCION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DIRECCION` (
    `id_direccion` INT NOT NULL AUTO_INCREMENT,
    `id_usuario` INT NOT NULL,
    `direccion_completa` TEXT NOT NULL,
    `ciudad` VARCHAR(100) NOT NULL,
    `departamento` VARCHAR(50) NOT NULL,
    `codigo_postal` VARCHAR(10) NULL,
    `principal` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id_direccion`),
    INDEX `fk_direccion_usuario_idx` (`id_usuario` ASC),
    CONSTRAINT `fk_direccion_usuario`
        FOREIGN KEY (`id_usuario`)
        REFERENCES `USUARIO` (`id_usuario`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla CATEGORIA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CATEGORIA` (
    `id_categoria` INT NOT NULL AUTO_INCREMENT,
    `nombre_categoria` VARCHAR(100) NOT NULL,
    `descripcion` TEXT NULL,
    PRIMARY KEY (`id_categoria`),
    UNIQUE INDEX `nombre_categoria_UNIQUE` (`nombre_categoria` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla PRODUCTO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PRODUCTO` (
    `id_producto` INT NOT NULL AUTO_INCREMENT,
    `nombre_producto` VARCHAR(200) NOT NULL,
    `descripcion` TEXT NULL,
    `precio_base` DECIMAL(10,2) NOT NULL,
    `id_categoria` INT NOT NULL,
    PRIMARY KEY (`id_producto`),
    INDEX `fk_producto_categoria_idx` (`id_categoria` ASC),
    CONSTRAINT `fk_producto_categoria`
        FOREIGN KEY (`id_categoria`)
        REFERENCES `CATEGORIA` (`id_categoria`)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla PRODUCTO_VARIANTE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PRODUCTO_VARIANTE` (
    `id_variante` INT NOT NULL AUTO_INCREMENT,
    `id_producto` INT NOT NULL,
    `talla` VARCHAR(10) NULL,
    `color` VARCHAR(50) NULL,
    `stock` INT NOT NULL DEFAULT 0,
    `precio_venta` DECIMAL(10,2) NULL,
    PRIMARY KEY (`id_variante`),
    INDEX `fk_variante_producto_idx` (`id_producto` ASC),
    UNIQUE INDEX `unique_producto_variante` (`id_producto` ASC, `talla` ASC, `color` ASC),
    CONSTRAINT `fk_variante_producto`
        FOREIGN KEY (`id_producto`)
        REFERENCES `PRODUCTO` (`id_producto`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla METODO_PAGO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `METODO_PAGO` (
    `id_metodo_pago` INT NOT NULL AUTO_INCREMENT,
    `nombre_metodo` VARCHAR(50) NOT NULL,
    `descripcion` TEXT NULL,
    PRIMARY KEY (`id_metodo_pago`),
    UNIQUE INDEX `nombre_metodo_UNIQUE` (`nombre_metodo` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla CARRITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CARRITO` (
    `id_carrito` INT NOT NULL AUTO_INCREMENT,
    `id_usuario` INT NOT NULL,
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `estado` ENUM('activo', 'procesado') NOT NULL DEFAULT 'activo',
    PRIMARY KEY (`id_carrito`),
    INDEX `fk_carrito_usuario_idx` (`id_usuario` ASC),
    CONSTRAINT `fk_carrito_usuario`
        FOREIGN KEY (`id_usuario`)
        REFERENCES `USUARIO` (`id_usuario`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla ITEM_CARRITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ITEM_CARRITO` (
    `id_item_carrito` INT NOT NULL AUTO_INCREMENT,
    `id_carrito` INT NOT NULL,
    `id_variante` INT NOT NULL,
    `cantidad` INT NOT NULL DEFAULT 1,
    `fecha_agregado` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_item_carrito`),
    INDEX `fk_item_carrito_carrito_idx` (`id_carrito` ASC),
    INDEX `fk_item_carrito_variante_idx` (`id_variante` ASC),
    UNIQUE INDEX `unique_carrito_variante` (`id_carrito` ASC, `id_variante` ASC),
    CONSTRAINT `fk_item_carrito_carrito`
        FOREIGN KEY (`id_carrito`)
        REFERENCES `CARRITO` (`id_carrito`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT `fk_item_carrito_variante`
        FOREIGN KEY (`id_variante`)
        REFERENCES `PRODUCTO_VARIANTE` (`id_variante`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT `chk_cantidad_positiva` CHECK (`cantidad` > 0)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla PEDIDO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PEDIDO` (
    `id_pedido` INT NOT NULL AUTO_INCREMENT,
    `id_usuario` INT NOT NULL,
    `id_metodo_pago` INT NOT NULL,
    `id_direccion` INT NOT NULL,
    `fecha_pedido` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `estado_pedido` ENUM('pendiente', 'pagado', 'enviado', 'entregado', 'cancelado') NOT NULL DEFAULT 'pendiente',
    `total` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`id_pedido`),
    INDEX `fk_pedido_usuario_idx` (`id_usuario` ASC),
    INDEX `fk_pedido_metodo_pago_idx` (`id_metodo_pago` ASC),
    INDEX `fk_pedido_direccion_idx` (`id_direccion` ASC),
    CONSTRAINT `fk_pedido_usuario`
        FOREIGN KEY (`id_usuario`)
        REFERENCES `USUARIO` (`id_usuario`)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT `fk_pedido_metodo_pago`
        FOREIGN KEY (`id_metodo_pago`)
        REFERENCES `METODO_PAGO` (`id_metodo_pago`)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT `fk_pedido_direccion`
        FOREIGN KEY (`id_direccion`)
        REFERENCES `DIRECCION` (`id_direccion`)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla DETALLE_PEDIDO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DETALLE_PEDIDO` (
    `id_detalle` INT NOT NULL AUTO_INCREMENT,
    `id_pedido` INT NOT NULL,
    `id_variante` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `precio_unitario` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`id_detalle`),
    INDEX `fk_detalle_pedido_pedido_idx` (`id_pedido` ASC),
    INDEX `fk_detalle_pedido_variante_idx` (`id_variante` ASC),
    CONSTRAINT `fk_detalle_pedido_pedido`
        FOREIGN KEY (`id_pedido`)
        REFERENCES `PEDIDO` (`id_pedido`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT `fk_detalle_pedido_variante`
        FOREIGN KEY (`id_variante`)
        REFERENCES `PRODUCTO_VARIANTE` (`id_variante`)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT `chk_detalle_cantidad_positiva` CHECK (`cantidad` > 0)
) ENGINE = InnoDB;

-- =====================================================
-- 3. DATOS INICIALES
-- =====================================================

-- Insertar roles
INSERT INTO `ROL` (`nombre_rol`, `descripcion`) VALUES
('Cliente', 'Usuario que realiza compras en la tienda'),
('Administrador', 'Usuario que gestiona el sistema, productos y pedidos');

-- Insertar métodos de pago
INSERT INTO `METODO_PAGO` (`nombre_metodo`, `descripcion`) VALUES
('PSE', 'Pago electrónico bancario'),
('Tarjeta Crédito', 'Pago con tarjeta de crédito'),
('Tarjeta Débito', 'Pago con tarjeta débito'),
('PayPal', 'Pago vía PayPal'),
('Contraentrega', 'Pago al recibir el producto');

-- Insertar categorías
INSERT INTO `CATEGORIA` (`nombre_categoria`, `descripcion`) VALUES
('Calzado Running', 'Zapatillas para correr - Tecnología Boost y Lightstrike'),
('Calzado Training', 'Zapatillas para entrenamiento y gimnasio'),
('Ropa Deportiva', 'Camisetas, sudaderas, pantalonetas para actividad física'),
('Accesorios', 'Gorras, morrales, medias y complementos'),
('Calzado Casual', 'Zapatos para uso diario - Estilo urbano'),
('Ediciones Especiales', 'Lanzamientos limitados y colaboraciones');

-- =====================================================
-- 5. DATOS DE PRUEBA
-- =====================================================

-- Insertar usuario de prueba
INSERT INTO `USUARIO` (`nombre_completo`, `email`, `contrasena_hash`, `telefono`) VALUES
('Juan Pérez', 'juan.perez@email.com', 'hash_de_prueba_123', '3001234567'),
('María Gómez', 'maria.gomez@email.com', 'hash_de_prueba_456', '3107654321');

-- Asignar roles a usuarios
INSERT INTO `USUARIO_ROL` (`id_usuario`, `id_rol`) VALUES
(1, 1), -- Juan es Cliente
(2, 1), -- María es Cliente
(2, 2); -- María también es Administradora

-- Insertar direcciones
INSERT INTO `DIRECCION` (`id_usuario`, `direccion_completa`, `ciudad`, `departamento`, `principal`) VALUES
(1, 'Carrera 45 #23-12, Apto 301', 'Medellín', 'Antioquia', 1),
(2, 'Calle 100 #15-80, Barrio El Prado', 'Barranquilla', 'Atlántico', 1);

-- Insertar productos de ejemplo
INSERT INTO `PRODUCTO` (`nombre_producto`, `descripcion`, `precio_base`, `id_categoria`) VALUES
('Adidas Ultraboost 22', 'Zapatillas running con tecnología Boost', 450000, 1),
('Adidas Forum Low', 'Zapatillas casual estilo retro', 320000, 5),
('Camiseta Adidas Entrenamiento', 'Camiseta transpirable para gym', 120000, 3);

-- Insertar variantes de productos
INSERT INTO `PRODUCTO_VARIANTE` (`id_producto`, `talla`, `color`, `stock`, `precio_venta`) VALUES
(1, '40', 'Negro', 15, 450000),
(1, '41', 'Negro', 12, 450000),
(1, '42', 'Negro', 8, 450000),
(1, '40', 'Blanco', 10, 450000),
(2, '38', 'Blanco/Azul', 20, 320000),
(2, '39', 'Blanco/Azul', 18, 320000),
(3, 'M', 'Azul', 25, 120000),
(3, 'L', 'Azul', 22, 120000),
(3, 'M', 'Negro', 30, 120000);

-- Insertar carrito para Juan
INSERT INTO `CARRITO` (`id_usuario`, `estado`) VALUES (1, 'activo');

-- Insertar items en el carrito de Juan
INSERT INTO `ITEM_CARRITO` (`id_carrito`, `id_variante`, `cantidad`) VALUES
(1, 1, 1),  -- Ultraboost talla 40 negro
(1, 7, 2);  -- Camiseta talla M azul (2 unidades)

-- Insertar un pedido de ejemplo para María
INSERT INTO `PEDIDO` (`id_usuario`, `id_metodo_pago`, `id_direccion`, `total`) VALUES
(2, 2, 2, 920000);  -- Tarjeta Crédito, dirección Barranquilla

-- Insertar detalles del pedido
INSERT INTO `DETALLE_PEDIDO` (`id_pedido`, `id_variante`, `cantidad`, `precio_unitario`) VALUES
(1, 5, 1, 320000),  -- Forum Low talla 38
(1, 8, 5, 120000);  -- 5 camisetas talla L azul
