package gestores;

import modelo.CategoriaRecurso;
import modelo.Prestamo;
import modelo.RecursoBase;
import modelo.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class GestorReportes {
    private final List<Prestamo> prestamos;

    public GestorReportes(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Map<RecursoBase, Long> recursosMasPrestados() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));
    }

    public Map<Usuario, Long> usuariosMasActivos() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));
    }

    public Map<String, Long> prestamosPorCategoria() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getCategoria().name(), Collectors.counting()));
    }

    public void mostrarReporteRecursosMasPrestados() {
        System.out.println("\n📊 Recursos más prestados:");
        System.out.printf("%-30s | %s\n", "Título", "Préstamos");
        System.out.println("-----------------------------------------------");
        recursosMasPrestados().forEach((recurso, cantidad) ->
                System.out.printf("%-30s | %d\n", recurso.getTitulo(), cantidad));
    }

    public void mostrarReporteUsuariosMasActivos() {
        System.out.println("\n👤 Usuarios más activos:");
        System.out.printf("%-25s | %s\n", "Nombre", "Cantidad");
        System.out.println("------------------------------------------");
        usuariosMasActivos().forEach((usuario, cantidad) ->
                System.out.printf("%-25s | %d\n", usuario.getNombre(), cantidad));
    }

    public void mostrarReportePorTipo() {
        System.out.println("\n📚 Préstamos por tipo de recurso:");
        System.out.printf("%-15s | %s\n", "Categoría", "Cantidad");
        System.out.println("-------------------------------");
        prestamosPorCategoria().forEach((tipo, cantidad) ->
                System.out.printf("%-15s | %d\n", tipo, cantidad));
    }

    public void exportarRecursosMasPrestados(String nombreArchivo) {
        try (java.io.FileWriter writer = new java.io.FileWriter(nombreArchivo)) {
            writer.write("Reporte de Recursos Más Prestados\n");
            writer.write("-------------------------------\n");
            for (var entry : recursosMasPrestados().entrySet()) {
                writer.write(entry.getKey().getTitulo() + " → " + entry.getValue() + " préstamos\n");
            }
            System.out.println("📁 Reporte exportado exitosamente a " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("❌ Error al exportar el reporte: " + e.getMessage());
        }
    }
}
