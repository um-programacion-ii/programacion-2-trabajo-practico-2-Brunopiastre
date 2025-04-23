package modelo;

public class Revista extends RecursoBase {
    public Revista(String titulo, String id) {
        super(id, titulo);
    }

    @Override
    public CategoriaRecurso getCategoria() {
        return CategoriaRecurso.REVISTA;
    }
}
