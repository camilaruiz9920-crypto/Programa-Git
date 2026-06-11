package com.adidas.tienda;

import com.adidas.tienda.dao.ProductoDAO;
import com.adidas.tienda.dao.UsuarioDAO;
import com.adidas.tienda.model.Producto;
import com.adidas.tienda.model.Usuario;
import java.util.Scanner;
import java.util.List;

/**
 * Clase principal que ejecuta el menú de la tienda virtual Adidas Colombia.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductoDAO productoDao = new ProductoDAO();
    private static final UsuarioDAO usuarioDao = new UsuarioDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcionPrincipal(opcion);
        } while (opcion != 3);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- TIENDA VIRTUAL ADIDAS COLOMBIA ---");
        System.out.println("1. Gestionar Productos");
        System.out.println("2. Gestionar Usuarios");
        System.out.println("3. Salir");
    }

    private static void procesarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1 -> menuProductos();
            case 2 -> menuUsuarios();
            case 3 -> System.out.println("Gracias por usar el sistema.");
            default -> System.out.println("Opción inválida.");
        }
    }

    // ==========================================
    // MENÚ Y MÉTODOS DE GESTIÓN DE PRODUCTOS
    // ==========================================
    private static void menuProductos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Listar todos los productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Insertar nuevo producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcionProductos(opcion);
        } while (opcion != 6);
    }

    private static void procesarOpcionProductos(int opcion) {
        switch (opcion) {
            case 1 -> listarProductos();
            case 2 -> buscarProductoPorId();
            case 3 -> insertarProducto();
            case 4 -> actualizarProducto();
            case 5 -> eliminarProducto();
            case 6 -> System.out.println("Volviendo...");
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void listarProductos() {
        List<Producto> productos = productoDao.listarTodos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void buscarProductoPorId() {
        int id = leerEntero("Ingrese ID del producto: ");
        Producto p = productoDao.buscarPorId(id);
        System.out.println(p != null ? p : "Producto no encontrado.");
    }

    private static void insertarProducto() {
        Producto p = new Producto();
        System.out.print("Nombre: "); p.setNombreProducto(scanner.nextLine());
        System.out.print("Descripción: "); p.setDescripcion(scanner.nextLine());
        p.setPrecioBase(leerDouble("Precio Base: "));
        p.setIdCategoria(leerEntero("ID Categoría: "));

        if (productoDao.insertar(p)) System.out.println("Producto guardado con éxito.");
    }

    private static void actualizarProducto() {
        int id = leerEntero("ID del producto a actualizar: ");
        Producto p = productoDao.buscarPorId(id);
        if (p == null) {
            System.out.println("Producto no existe.");
            return;
        }
        System.out.print("Nuevo Nombre (" + p.getNombreProducto() + "): "); p.setNombreProducto(scanner.nextLine());
        System.out.print("Nueva Descripción (" + p.getDescripcion() + "): "); p.setDescripcion(scanner.nextLine());
        p.setPrecioBase(leerDouble("Nuevo Precio Base (" + p.getPrecioBase() + "): "));
        p.setIdCategoria(leerEntero("Nuevo ID Categoría (" + p.getIdCategoria() + "): "));
        
        if (productoDao.actualizar(p)) System.out.println("Actualizado correctamente.");
    }

    private static void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");
        if (productoDao.eliminar(id)) System.out.println("Eliminado con éxito.");
        else System.out.println("No se pudo eliminar.");
    }

    // ==========================================
    // MENÚ Y MÉTODOS DE GESTIÓN DE USUARIOS
    // ==========================================
    private static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Listar todos los usuarios");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Buscar usuario por Email");
            System.out.println("4. Registrar nuevo usuario");
            System.out.println("5. Actualizar usuario");
            System.out.println("6. Eliminar usuario (Físico)");
            System.out.println("7. Desactivar usuario (Lógico)");
            System.out.println("8. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcionUsuarios(opcion);
        } while (opcion != 8);
    }

    private static void procesarOpcionUsuarios(int opcion) {
        switch (opcion) {
            case 1 -> listarUsuarios();
            case 2 -> buscarUsuarioPorId();
            case 3 -> buscarUsuarioPorEmail();
            case 4 -> registrarUsuario();
            case 5 -> actualizarUsuario();
            case 6 -> eliminarUsuario();
            case 7 -> desactivarUsuario();
            case 8 -> System.out.println("Volviendo...");
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioDao.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            usuarios.forEach(u -> System.out.println("ID: " + u.getIdUsuario() + " | Nombre: " + u.getNombreCompleto() + " | Email: " + u.getEmail() + " | Teléfono: " + u.getTelefono() + " | Activo: " + (u.isActivo() ? "Sí" : "No")));
        }
    }

    private static void buscarUsuarioPorId() {
        int id = leerEntero("Ingrese ID del usuario: ");
        Usuario u = usuarioDao.buscarPorId(id);
        if (u != null) {
            System.out.println("Usuario encontrado:\n" + u + "\nTeléfono: " + u.getTelefono() + "\nEstado: " + (u.isActivo() ? "Activo" : "Inactivo") + "\nFecha Registro: " + u.getFechaRegistro());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void buscarUsuarioPorEmail() {
        System.out.print("Ingrese Email del usuario: ");
        String email = scanner.nextLine();
        Usuario u = usuarioDao.buscarPorEmail(email);
        if (u != null) {
            System.out.println("Usuario encontrado:\n" + u + "\nTeléfono: " + u.getTelefono() + "\nEstado: " + (u.isActivo() ? "Activo" : "Inactivo") + "\nFecha Registro: " + u.getFechaRegistro());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void registrarUsuario() {
        Usuario u = new Usuario();
        System.out.print("Nombre completo: "); u.setNombreCompleto(scanner.nextLine());
        System.out.print("Email: "); u.setEmail(scanner.nextLine());
        System.out.print("Contraseña (Hash): "); u.setContrasenaHash(scanner.nextLine());
        System.out.print("Teléfono: "); u.setTelefono(scanner.nextLine());

        if (usuarioDao.insertar(u)) System.out.println("Usuario registrado con éxito.");
    }

    private static void actualizarUsuario() {
        int id = leerEntero("ID del usuario a actualizar: ");
        Usuario u = usuarioDao.buscarPorId(id);
        if (u == null) {
            System.out.println("Usuario no existe.");
            return;
        }
        System.out.print("Nuevo Nombre Completo (" + u.getNombreCompleto() + "): "); u.setNombreCompleto(scanner.nextLine());
        System.out.print("Nuevo Email (" + u.getEmail() + "): "); u.setEmail(scanner.nextLine());
        System.out.print("Nueva Contraseña Hash (" + u.getContrasenaHash() + "): "); u.setContrasenaHash(scanner.nextLine());
        System.out.print("Nuevo Teléfono (" + u.getTelefono() + "): "); u.setTelefono(scanner.nextLine());
        System.out.print("¿Activo? (true/false) (" + u.isActivo() + "): ");
        while (!scanner.hasNextBoolean()) {
            System.out.print("Error. Ingrese true o false: ");
            scanner.next();
        }
        u.setActivo(scanner.nextBoolean());
        scanner.nextLine(); // Limpiar buffer

        if (usuarioDao.actualizar(u)) System.out.println("Usuario actualizado correctamente.");
    }

    private static void eliminarUsuario() {
        int id = leerEntero("ID del usuario a eliminar (Físico): ");
        if (usuarioDao.eliminar(id)) System.out.println("Usuario eliminado con éxito de la base de datos.");
        else System.out.println("No se pudo eliminar el usuario (verifique si tiene pedidos registrados).");
    }

    private static void desactivarUsuario() {
        int id = leerEntero("ID del usuario a desactivar (Lógico): ");
        if (usuarioDao.desactivar(id)) System.out.println("Usuario desactivado con éxito (activo = 0).");
        else System.out.println("No se pudo desactivar el usuario.");
    }

    // ==========================================
    // MÉTODOS DE LECTURA Y VALIDACIÓN DE CONSOLA
    // ==========================================
    private static int leerEntero(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Error. Ingrese un número entero: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return val;
    }

    private static double leerDouble(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextDouble()) {
            System.out.print("Error. Ingrese un precio válido: ");
            scanner.next();
        }
        double val = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer
        return val;
    }
}
