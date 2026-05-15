# Tienda Virtual Adidas Colombia - Módulo Backend (Versión Extendida)

Este proyecto es el resultado de la evidencia **GA6-220501096-AA2-EV03**. Consiste en un sistema backend robusto desarrollado en Java utilizando JDBC puro, diseñado para gestionar una tienda virtual completa con usuarios, productos, variantes, carritos y pedidos.

## 🚀 Características Principales
- **Arquitectura DAO**: Separación clara entre la lógica de negocio y el acceso a datos.
- **Esquema Relacional Completo**: Gestión de Roles, Direcciones, Categorías, Productos con Variantes (talla/color) y Pedidos.
- **Seguridad**: Uso de `PreparedStatement` para prevenir SQL Injection.
- **Documentación**: Código estandarizado y documentado.

## 🛠️ Requisitos del Sistema
- **Java JDK 17** o superior.
- **MySQL Server 8.0**.
- **Driver JDBC**: MySQL Connector/J (incluido en la carpeta `lib/`).

## 📂 Estructura del Proyecto
- `src/com/adidas/tienda/db`: Gestión de conexión y pruebas.
- `src/com/adidas/tienda/model`: POJOs que representan las tablas de la BD.
- `src/com/adidas/tienda/dao`: Objetos de Acceso a Datos (CRUD).
- `src/com/adidas/tienda/Main.java`: Menú interactivo por consola.
- `lib/`: Contiene `mysql-connector.jar` para la conexión.
- `script_bd_tienda_adidas.sql`: Script para regenerar la base de datos completa.

## ⚙️ Configuración Inicial

1. **Base de Datos**: 
   Ejecuta el archivo `script_bd_tienda_adidas.sql` en tu MySQL Workbench o terminal para crear la base de datos `tienda_adidas` con sus datos iniciales.

2. **Conexión**:
   Abre `src/com/adidas/tienda/db/ConexionBD.java` y ajusta tus credenciales:
   ```java
   private static final String USUARIO = "tu_usuario";
   private static final String PASSWORD = "tu_password";
   ```

3. **Prueba de Conexión**:
   Ejecuta la clase `com.adidas.tienda.db.TestConexion` para verificar que Java se comunica con MySQL.

## 💻 Ejecución

### Desde la Terminal (Windows/PowerShell):
Para compilar y ejecutar manualmente:
```powershell
# Compilar
javac -d bin -sourcepath src src/com/adidas/tienda/Main.java

# Ejecutar
java -cp "bin;lib/mysql-connector.jar" com.adidas.tienda.Main
```

### Usando el script automatizado:
Haz doble clic en el archivo `ejecutar.bat` en la raíz del proyecto para abrir el menú directamente.

---
**Autor:** Jose Narvaez & Ariagna Camila Conde
**Institución:** SENA - Análisis y Desarrollo de Software (ADSO)
**Año:** 2026
