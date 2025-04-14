package modelo;

import java.time.LocalDateTime;
import interfaces.RecursoDigital;
import modelo.Usuario;

public class Reserva {
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDateTime fechaReserva;

    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }
}
