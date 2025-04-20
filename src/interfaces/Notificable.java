package interfaces;

import modelo.Notificacion;
import java.util.List;

public interface Notificable {
    void enviarNotificacion(String mensaje);
    List<Notificacion> getNotificacionesPendientes();
}
