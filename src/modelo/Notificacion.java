package modelo;

public class Notificacion {
    private final String mensaje;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "🔔 " + mensaje;
    }
}
