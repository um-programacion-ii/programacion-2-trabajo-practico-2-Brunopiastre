package servicios;

import interfaces.Notificable;
import modelo.Notificacion;

import java.util.ArrayList;
import java.util.List;

public class NotificadorConsola implements Notificable {
    private final List<Notificacion> notificaciones = new ArrayList<>();

    @Override
    public void enviarNotificacion(String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje);
        notificaciones.add(notificacion);
        System.out.println("🔔 NOTIFICACIÓN: " + mensaje);
    }

    @Override
    public List<Notificacion> getNotificacionesPendientes() {
        return notificaciones;
    }
}
