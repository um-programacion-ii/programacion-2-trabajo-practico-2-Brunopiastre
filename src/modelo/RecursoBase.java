package modelo;

import interfaces.Prestable;
import interfaces.RecursoDigital;

public abstract class RecursoBase implements RecursoDigital, Prestable {
    protected String id;
    protected String titulo;
    protected EstadoRecurso estado;
    protected Usuario prestadoA;

    public RecursoBase(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.estado = EstadoRecurso.DISPONIBLE;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getIdentificador() {
        return id;
    }

    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }

    @Override
    public void actualizarEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    @Override
    public boolean estaDisponible() {
        return estado == EstadoRecurso.DISPONIBLE;
    }

    @Override
    public void prestar(Usuario usuario) {
        if (!estaDisponible()) throw new IllegalStateException("Recurso no disponible");
        this.estado = EstadoRecurso.PRESTADO;
        this.prestadoA = usuario;
    }

    @Override
    public void devolver() {
        this.estado = EstadoRecurso.DISPONIBLE;
        this.prestadoA = null;
    }

    @Override
    public String toString() {
        return titulo + " [" + estado + "]";
    }

    public abstract TipoRecurso getTipo();
}
