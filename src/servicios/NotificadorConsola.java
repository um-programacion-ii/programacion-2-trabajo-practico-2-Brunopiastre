package servicios;

import interfaces.Notificable;
import modelo.Notificacion;
import modelo.Usuario;
import gestores.GestorUsuarios;

import java.util.ArrayList;
import java.util.List;

public class NotificadorConsola implements Notificable {
    private final List<Notificacion> pendientes = new ArrayList<>();
    private final GestorUsuarios gestorUsuarios = new GestorUsuarios(); // o puede ser inyectado

    @Override
    public void enviarNotificacion(String mensaje) {
        for (Usuario usuario : gestorUsuarios.getTodos().values()) {
            if (mensaje.contains(usuario.getNombre())) {
                if (!usuario.isRecibirNotificaciones()) {
                    return; // El usuario no desea recibir notificaciones
                }
            }
        }

        System.out.println("🔔 NOTIFICACIÓN: " + mensaje);
        pendientes.add(new Notificacion(mensaje));
    }

    @Override
    public List<Notificacion> getNotificacionesPendientes() {
        return pendientes;
    }
}
