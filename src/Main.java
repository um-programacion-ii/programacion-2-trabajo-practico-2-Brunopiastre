import modelo.*;
import gestores.GestorReportes;
import servicios.AlertaVencimiento;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Registro de usuarios
        Usuario usuario1 = new Usuario("U001", "Juan Pérez", "juan@email.com");
        Usuario usuario2 = new Usuario("U002", "María López", "maria@email.com");

        // Registro de recursos
        Libro libro = new Libro("El Principito", "L001");
        Revista revista = new Revista("Muy Interesante", "R001");
        Audiolibro audio = new Audiolibro("Cuentos en voz alta", "A001");

        // Préstamos simulados
        List<Prestamo> prestamos = new ArrayList<>();
        prestamos.add(new Prestamo(usuario1, libro));
        prestamos.add(new Prestamo(usuario2, revista));
        prestamos.add(new Prestamo(usuario2, audio));
        prestamos.add(new Prestamo(usuario1, audio));

        // Reportes
        GestorReportes reportes = new GestorReportes(prestamos);

        System.out.println("\n=== 📘 Reporte: Recursos Más Prestados ===");
        reportes.recursosMasPrestados().forEach(entry ->
                System.out.println(entry.getKey().getTitulo() + " → " + entry.getValue() + " préstamos"));

        System.out.println("\n=== 👤 Reporte: Usuarios Más Activos ===");
        reportes.usuariosMasActivos().forEach(entry ->
                System.out.println(entry.getKey().getNombre() + " → " + entry.getValue() + " préstamos"));

        System.out.println("\n=== 🧾 Reporte: Préstamos por Tipo de Recurso ===");
        reportes.prestamosPorTipo().forEach((tipo, cantidad) ->
                System.out.println(tipo + " → " + cantidad));

        // Alertas de vencimiento
        System.out.println("\n=== 🛎️  Alertas de Vencimiento ===");
        AlertaVencimiento alertas = new AlertaVencimiento();
        alertas.verificarVencimientos(prestamos);
    }
}
