package modelo;

public class Audiolibro extends RecursoBase {
    public Audiolibro(String titulo, String id) {
        super(id, titulo);
    }

    @Override
    public CategoriaRecurso getCategoria() {
        return CategoriaRecurso.AUDIOLIBRO;
    }
}
