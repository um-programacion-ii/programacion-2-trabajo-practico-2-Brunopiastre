package modelo;

public class Libro extends RecursoBase {
    private String autor;

    public Libro(String id, String titulo, String autor) {
        super(id, titulo);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Libro: " + titulo + " de " + autor + " [" + estado + "]";
    }
}
