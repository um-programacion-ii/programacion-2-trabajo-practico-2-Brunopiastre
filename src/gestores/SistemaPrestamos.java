package gestores;

import modelo.Reserva;
import modelo.Usuario;
import modelo.EstadoRecurso;
import interfaces.RecursoDigital;

public class SistemaPrestamos {

    private final SistemaReservas sistemaReservas = new SistemaReservas();

    public void devolverRecurso(RecursoDigital recurso) {
        recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("📦 Recurso '" + recurso.getIdentificador() + "' devuelto con éxito.");

        if (sistemaReservas.tieneReservasPendientes(recurso.getIdentificador())) {
            Reserva siguiente = sistemaReservas.obtenerProximaReserva(recurso.getIdentificador());
            System.out.println("🔔 Notificar a " + siguiente.getUsuario().getNombre()
                    + ": el recurso '" + recurso.getIdentificador() + "' está disponible.");

            // 👇 Si querés hacer el préstamo automático, podés hacer esto:
            // recurso.prestar(siguiente.getUsuario());
            // recurso.actualizarEstado(EstadoRecurso.PRESTADO);
        }
    }

    // Podés agregar esto más adelante:
    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) {
        // Validar si está disponible o reservar si no está
        // recurso.prestar(usuario);
    }

    // Getters, setters o más lógica si es necesario
}
