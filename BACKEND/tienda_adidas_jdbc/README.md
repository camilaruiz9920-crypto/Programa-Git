# Tienda Virtual Adidas Colombia - Módulo Backend

Este proyecto es el resultado de la evidencia **GA7-220501096-AA2-EV01: Codificación de módulos del software**. Consiste en un módulo backend desarrollado en Java utilizando JDBC puro para la gestión de inventario de productos.

## Tecnologías Utilizadas
- **Lenguaje:** Java 17+
- **Base de Datos:** MySQL 8.0
- **Conectividad:** JDBC (Java Database Connectivity)
- **Driver:** MySQL Connector/J

## Estructura del Proyecto
- `src/com/adidas/tienda/db`: Gestión de conexión.
- `src/com/adidas/tienda/model`: Entidades del sistema.
- `src/com/adidas/tienda/dao`: Lógica de acceso a datos (CRUD).
- `src/com/adidas/tienda/Main.java`: Punto de entrada con menú interactivo.
- `lib/`: Directorio destinado al driver JDBC.

## Instrucciones de Ejecución
1. **Base de Datos**: Ejecute el archivo `script_bd_tienda_adidas.sql` en su servidor MySQL para crear la base de datos y la tabla.
2. **Configuración**: Abra `ConexionBD.java` y ajuste las credenciales (`USUARIO`, `PASSWORD`) si es necesario.
3. **Librería**: Asegúrese de agregar el JAR del driver `mysql-connector-j` a su classpath/Build Path.
4. **Compilación**: 
   ```bash
   javac -d bin src/com/adidas/tienda/**/*.java
   ```
5. **Ejecución**:
   ```bash
   java -cp "bin;lib/*" com.adidas.tienda.Main
   ```

## Requisitos de Calidad
- Implementación de `PreparedStatement` para seguridad.
- Manejo de excepciones con `try-with-resources`.
- Estándares de nomenclatura de Java (CamelCase).
- Comentarios Javadoc.

---
**Autor:** Jose Narvaez
**Institución:** SENA ADSO
