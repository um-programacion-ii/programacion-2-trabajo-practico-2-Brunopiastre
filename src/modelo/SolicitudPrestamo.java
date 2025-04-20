package modelo;

public class SolicitudPrestamo {
    private final Usuario usuario;
    private final RecursoBase recurso;

    public SolicitudPrestamo(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoBase getRecurso() {
        return recurso;
    }
}
