package servicios;

import modelo.Prestamo;

import java.time.LocalDate;
import java.util.List;

public class AlertaVencimiento {

    public void verificarVencimientos(List<Prestamo> prestamosActivos) {
        LocalDate hoy = LocalDate.now();

        for (Prestamo prestamo : prestamosActivos) {
            LocalDate vencimiento = prestamo.getFechaDevolucion();

            if (vencimiento != null) {
                if (vencimiento.equals(hoy.plusDays(1))) {
                    System.out.println("⚠️  Alerta: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' vence mañana para el usuario " + prestamo.getUsuario().getNombre());
                } else if (vencimiento.equals(hoy)) {
                    System.out.println("❗ El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' vence HOY para el usuario " + prestamo.getUsuario().getNombre());
                } else if (vencimiento.isBefore(hoy)) {
                    System.out.println("⛔ El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' ya está vencido para el usuario " + prestamo.getUsuario().getNombre());
                }
            }
        }
    }
}
