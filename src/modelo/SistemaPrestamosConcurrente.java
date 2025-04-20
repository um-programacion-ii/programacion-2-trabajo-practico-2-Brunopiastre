package gestores;

import modelo.SolicitudPrestamo;
import modelo.Usuario;
import modelo.RecursoBase;
import modelo.Prestamo;
import modelo.Reserva;
import servicios.ServicioNotificaciones;

import java.util.concurrent.*;
import java.util.*;

public class SistemaPrestamosConcurrente {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes = new LinkedBlockingQueue<>();
    private final List<Prestamo> prestamos = new ArrayList<>();
    private final SistemaReservas sistemaReservas;
    private final ServicioNotificaciones servicioNotificaciones;
    private final ExecutorService executor;

    public SistemaPrestamosConcurrente(SistemaReservas sistemaReservas, ServicioNotificaciones servicioNotificaciones) {
        this.sistemaReservas = sistemaReservas;
        this.servicioNotificaciones = servicioNotificaciones;
        this.executor = Executors.newSingleThreadExecutor();
        iniciarProcesador();
    }

    private void iniciarProcesador() {
        executor.execute(() -> {
            try {
                while (true) {
                    SolicitudPrestamo solicitud = colaSolicitudes.take();
                    procesarSolicitud(solicitud);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("🛑 Procesador de préstamos detenido.");
            }
        });
    }

    public void agregarSolicitud(SolicitudPrestamo solicitud) {
        colaSolicitudes.add(solicitud);
    }

    private void procesarSolicitud(SolicitudPrestamo solicitud) {
        Usuario usuario = solicitud.getUsuario();
        RecursoBase recurso = solicitud.getRecurso();

        if (recurso.estaDisponible()) {
            recurso.prestar(usuario);
            Prestamo prestamo = new Prestamo(usuario, recurso);
            synchronized (prestamos) {
                prestamos.add(prestamo);
            }
            System.out.println("✅ [HILO] Préstamo procesado: " + usuario.getNombre() + " → " + recurso.getTitulo());
        } else {
            sistemaReservas.realizarReserva(usuario, recurso);
        }
    }

    public void devolverRecurso(String idRecurso) {
        synchronized (prestamos) {
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getRecurso().getIdentificador().equals(idRecurso)) {
                    prestamo.getRecurso().devolver();
                    System.out.println("✅ Recurso devuelto correctamente.");

                    if (sistemaReservas.hayReservasPendientes(idRecurso)) {
                        Reserva siguiente = sistemaReservas.procesarProximaReserva(idRecurso);
                        prestamo.getRecurso().prestar(siguiente.getUsuario());
                        servicioNotificaciones.enviar("El recurso '" + prestamo.getRecurso().getTitulo()
                                + "' fue entregado automáticamente a " + siguiente.getUsuario().getNombre());
                    }

                    return;
                }
            }
        }
    }

    public void apagarProcesador() {
        executor.shutdownNow();
    }

    public List<Prestamo> getTodos() {
        return prestamos;
    }

}
