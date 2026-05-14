package com.adidas.tienda;

import com.adidas.tienda.dao.ProductoDAO;
import com.adidas.tienda.model.Producto;
import java.util.Scanner;
import java.util.List;

/**
 * Clase principal que ejecuta el menú de la tienda virtual Adidas Colombia.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductoDAO dao = new ProductoDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- TIENDA VIRTUAL ADIDAS COLOMBIA ---");
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Insertar nuevo producto");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Buscar por categoría (Extra)");
        System.out.println("7. Salir");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> listarProductos();
            case 2 -> buscarPorId();
            case 3 -> insertarProducto();
            case 4 -> actualizarProducto();
            case 5 -> eliminarProducto();
            case 6 -> System.out.println("Función de categoría en desarrollo...");
            case 7 -> System.out.println("Gracias por usar el sistema.");
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void listarProductos() {
        List<Producto> productos = dao.listarTodos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void buscarPorId() {
        int id = leerEntero("Ingrese ID del producto: ");
        Producto p = dao.buscarPorId(id);
        System.out.println(p != null ? p : "Producto no encontrado.");
    }

    private static void insertarProducto() {
        Producto p = new Producto();
        System.out.print("Nombre: "); p.setNombre(scanner.nextLine());
        System.out.print("Descripción: "); p.setDescripcion(scanner.nextLine());
        p.setPrecio(leerDouble("Precio: "));
        p.setStock(leerEntero("Stock: "));
        System.out.print("Categoría: "); p.setCategoria(scanner.nextLine());

        if (dao.insertar(p)) System.out.println("Producto guardado con éxito.");
    }

    private static void actualizarProducto() {
        int id = leerEntero("ID del producto a actualizar: ");
        Producto p = dao.buscarPorId(id);
        if (p == null) {
            System.out.println("Producto no existe.");
            return;
        }
        System.out.print("Nuevo Nombre (" + p.getNombre() + "): "); p.setNombre(scanner.nextLine());
        p.setPrecio(leerDouble("Nuevo Precio (" + p.getPrecio() + "): "));
        p.setStock(leerEntero("Nuevo Stock (" + p.getStock() + "): "));
        
        if (dao.actualizar(p)) System.out.println("Actualizado correctamente.");
    }

    private static void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");
        if (dao.eliminar(id)) System.out.println("Eliminado con éxito.");
        else System.out.println("No se pudo eliminar.");
    }

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
