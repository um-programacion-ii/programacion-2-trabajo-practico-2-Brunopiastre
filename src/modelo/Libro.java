package modelo;

public class Libro extends RecursoBase {

    public Libro(String titulo, String id) {
        super(id, titulo);
    }

    @Override
    public TipoRecurso getTipo() {
        return TipoRecurso.LIBRO;
    }
}
