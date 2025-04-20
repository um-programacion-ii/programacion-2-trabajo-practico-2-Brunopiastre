package modelo;

public class Libro extends RecursoBase {
    private final CategoriaRecurso categoria;

    public Libro(String titulo, String id, CategoriaRecurso categoria) {
        super(id, titulo);
        this.categoria = categoria;
    }

    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
    }
}
