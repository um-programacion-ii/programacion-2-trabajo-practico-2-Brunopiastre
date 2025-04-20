package gestores;

import modelo.RecursoBase;
import modelo.Usuario;
import modelo.Prestamo;

import java.io.FileWriter;
import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;

public class GestorReportes {
    private final List<Prestamo> prestamos;

    public GestorReportes(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Map.Entry<RecursoBase, Long>> recursosMasPrestados() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<RecursoBase, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Usuario, Long>> usuariosMasActivos() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public Map<String, Long> prestamosPorTipo() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getTipo().name(), Collectors.counting()));
    }

    public void mostrarReporteRecursosMasPrestados() {
        System.out.println("\n📊 Recursos más prestados:");
        System.out.printf("%-30s | %s\n", "Título", "Préstamos");
        System.out.println("-----------------------------------------------");
        recursosMasPrestados().forEach(entry ->
                System.out.printf("%-30s | %d\n", entry.getKey().getTitulo(), entry.getValue()));
    }

    public void mostrarReporteUsuariosMasActivos() {
        System.out.println("\n👤 Usuarios más activos:");
        System.out.printf("%-25s | %s\n", "Nombre", "Cantidad");
        System.out.println("------------------------------------------");
        usuariosMasActivos().forEach(entry ->
                System.out.printf("%-25s | %d\n", entry.getKey().getNombre(), entry.getValue()));
    }

    public void mostrarReportePorTipo() {
        System.out.println("\n📚 Préstamos por tipo de recurso:");
        System.out.printf("%-15s | %s\n", "Tipo", "Cantidad");
        System.out.println("-------------------------------");
        prestamosPorTipo().forEach((tipo, cantidad) ->
                System.out.printf("%-15s | %d\n", tipo, cantidad));
    }

    public void exportarRecursosMasPrestados(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("Reporte de Recursos Más Prestados\n");
            writer.write("-------------------------------\n");
            for (var entry : recursosMasPrestados()) {
                writer.write(entry.getKey().getTitulo() + " → " + entry.getValue() + " préstamos\n");
            }
            System.out.println("📁 Reporte exportado exitosamente a " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("❌ Error al exportar el reporte: " + e.getMessage());
        }
    }
}
