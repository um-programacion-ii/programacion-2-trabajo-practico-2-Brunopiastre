import modelo.*;
import gestores.GestorReportes;
import servicios.AlertaVencimiento;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Registro de Usuarios
        Usuario usuario1 = new Usuario("U001", "Juan Pérez", "juan@email.com");
        Usuario usuario2 = new Usuario("U002", "María López", "maria@email.com");

        // Registro de Libros (sin pasar EstadoRecurso)
        Libro libro1 = new Libro("El Principito", "L001", CategoriaRecurso.FICCION);
        Libro libro2 = new Libro("Cien Años de Soledad", "L002", CategoriaRecurso.DRAMA);
        Libro libro3 = new Libro("Cálculo Infinitesimal", "L003", CategoriaRecurso.EDUCACION);

        // Préstamos simulados
        List<Prestamo> prestamos = new ArrayList<>();
        prestamos.add(new Prestamo(usuario1, libro1));
        prestamos.add(new Prestamo(usuario1, libro2));
        prestamos.add(new Prestamo(usuario2, libro1));
        prestamos.add(new Prestamo(usuario2, libro3));

        // Reportes
        GestorReportes reportes = new GestorReportes(prestamos);

        System.out.println("\n=== 📘 Reporte: Recursos Más Prestados ===");
        reportes.recursosMasPrestados().forEach(entry ->
                System.out.println(entry.getKey().getTitulo() + " → " + entry.getValue() + " préstamos"));

        System.out.println("\n=== 👤 Reporte: Usuarios Más Activos ===");
        reportes.usuariosMasActivos().forEach(entry ->
                System.out.println(entry.getKey().getNombre() + " → " + entry.getValue() + " préstamos"));

        System.out.println("\n=== 📂 Reporte: Préstamos por Categoría ===");
        reportes.prestamosPorCategoria().forEach((categoria, cantidad) ->
                System.out.println(categoria + " → " + cantidad));

        // Alertas
        System.out.println("\n=== 🛎️  Alertas de Vencimiento ===");
        AlertaVencimiento alertas = new AlertaVencimiento();
        alertas.verificarVencimientos(prestamos);
    }
}
