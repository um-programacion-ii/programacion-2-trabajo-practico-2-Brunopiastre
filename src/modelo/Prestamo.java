package modelo;

import java.time.LocalDate;

public class Prestamo {
    private final Usuario usuario;
    private final RecursoBase recurso;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(7); // 7 días por defecto
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoBase getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void renovar() {
        this.fechaDevolucion = fechaDevolucion.plusDays(7);
    }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(fechaDevolucion);
    }
}
