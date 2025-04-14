package gestores;

import interfaces.RecursoDigital;
import modelo.Reserva;
import modelo.Usuario;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.*;

public class SistemaReservas {
    private final Map<String, BlockingQueue<Reserva>> reservasPendientes = new HashMap<>();

    public void realizarReserva(Usuario usuario, RecursoDigital recurso) {
        reservasPendientes
                .computeIfAbsent(recurso.getIdentificador(), k -> new LinkedBlockingQueue<>())
                .add(new Reserva(usuario, recurso));
        System.out.println("✅ Reserva registrada para " + usuario.getNombre() + " sobre recurso: " + recurso.getIdentificador());
    }

    public Reserva obtenerProximaReserva(String idRecurso) {
        BlockingQueue<Reserva> cola = reservasPendientes.get(idRecurso);
        return (cola != null) ? cola.poll() : null;
    }

    public boolean tieneReservasPendientes(String idRecurso) {
        BlockingQueue<Reserva> cola = reservasPendientes.get(idRecurso);
        return cola != null && !cola.isEmpty();
    }
}
