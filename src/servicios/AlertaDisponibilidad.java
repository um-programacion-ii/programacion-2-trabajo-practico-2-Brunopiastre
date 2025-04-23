package servicios;

import modelo.Reserva;
import modelo.RecursoBase;

public class AlertaDisponibilidad {
    public void notificarDisponibilidad(RecursoBase recurso, Reserva reserva) {
        String mensaje = "📢 ALERTA DE DISPONIBILIDAD: El recurso '" + recurso.getTitulo() +
                "' ahora está disponible para el usuario " + reserva.getUsuario().getNombre();
        System.out.println(mensaje);
        HistorialAlertas.registrar(mensaje);
    }
}
