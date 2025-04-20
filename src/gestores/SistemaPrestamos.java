package gestores;

import modelo.Prestamo;
import modelo.RecursoBase;
import modelo.Usuario;
import modelo.Reserva;

import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {
    private final List<Prestamo> prestamos = new ArrayList<>();
    private final SistemaReservas sistemaReservas;

    public SistemaPrestamos(SistemaReservas sistemaReservas) {
        this.sistemaReservas = sistemaReservas;
    }

    public boolean realizarPrestamo(Usuario usuario, RecursoBase recurso) {
        if (usuario == null || recurso == null || !recurso.estaDisponible()) return false;

        Prestamo prestamo = new Prestamo(usuario, recurso);
        prestamos.add(prestamo);
        recurso.prestar(usuario);
        return true;
    }

    public boolean devolverRecurso(String idRecurso) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getRecurso().getIdentificador().equals(idRecurso)) {
                prestamo.getRecurso().devolver();
                System.out.println("✅ Recurso devuelto correctamente.");

                if (sistemaReservas.hayReservasPendientes(idRecurso)) {
                    Reserva siguiente = sistemaReservas.procesarProximaReserva(idRecurso);
                    prestamo.getRecurso().prestar(siguiente.getUsuario());
                    System.out.println("📢 El recurso fue entregado automáticamente a: " + siguiente.getUsuario().getNombre());
                }

                return true;
            }
        }
        return false;
    }

    public List<Prestamo> getTodos() {
        return prestamos;
    }
}
