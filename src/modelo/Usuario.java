package modelo;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private boolean recibirNotificaciones = true;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRecibirNotificaciones() {
        return recibirNotificaciones;
    }

    public void setRecibirNotificaciones(boolean recibirNotificaciones) {
        this.recibirNotificaciones = recibirNotificaciones;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}
