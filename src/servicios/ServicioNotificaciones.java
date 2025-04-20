package servicios;

import interfaces.Notificable;

public class ServicioNotificaciones {
    private final Notificable canal;

    public ServicioNotificaciones(Notificable canal) {
        this.canal = canal;
    }

    public void enviar(String mensaje) {
        canal.enviarNotificacion(mensaje);
    }
}
