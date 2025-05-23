package gestores;

import modelo.EstadoReserva;
import modelo.Reserva;
import modelo.RecursoBase;
import modelo.Usuario;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SistemaReservas {
    private final Map<String, Queue<Reserva>> reservasPorRecurso = new ConcurrentHashMap<>();

    public void realizarReserva(Usuario usuario, RecursoBase recurso) {
        reservasPorRecurso
                .computeIfAbsent(recurso.getIdentificador(), k -> new LinkedList<>())
                .add(new Reserva(usuario, recurso));

        System.out.println("📌 Reserva registrada para el recurso '" + recurso.getTitulo() +
                "' por el usuario " + usuario.getNombre());
    }

    public Reserva obtenerProximaReserva(String idRecurso) {
        Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
        return (cola != null && !cola.isEmpty()) ? cola.peek() : null;
    }

    public Reserva procesarProximaReserva(String idRecurso) {
        Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
        if (cola != null) {
            Reserva siguiente = cola.poll();
            if (siguiente != null) {
                siguiente.setEstado(EstadoReserva.ATENDIDA);
            }
            return siguiente;
        }
        return null;
    }

    public boolean hayReservasPendientes(String idRecurso) {
        Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
        return cola != null && !cola.isEmpty();
    }

    public void mostrarCola(String idRecurso) {
        Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
        if (cola == null || cola.isEmpty()) {
            System.out.println("📭 No hay reservas pendientes para el recurso.");
        } else {
            System.out.println("📋 Cola de reservas:");
            for (Reserva r : cola) {
                System.out.println("- " + r.getUsuario().getNombre()
                        + " (reservado en " + r.getFechaReserva()
                        + ") Estado: " + r.getEstado());
            }
        }
    }
}
