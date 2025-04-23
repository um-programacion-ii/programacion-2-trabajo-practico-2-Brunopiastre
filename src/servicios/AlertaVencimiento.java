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
                String mensaje;
                if (vencimiento.equals(hoy.plusDays(1))) {
                    mensaje = "🔔 ALERTA: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' vence MAÑANA para el usuario " + prestamo.getUsuario().getNombre();
                } else if (vencimiento.equals(hoy)) {
                    mensaje = "⚠️ URGENTE: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' vence HOY para el usuario " + prestamo.getUsuario().getNombre();
                } else if (vencimiento.isBefore(hoy)) {
                    mensaje = "⛔ VENCIDO: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' ya venció para el usuario " + prestamo.getUsuario().getNombre();
                } else {
                    continue;
                }

                System.out.println(mensaje);
                HistorialAlertas.registrar(mensaje);
            }
        }
    }
}
