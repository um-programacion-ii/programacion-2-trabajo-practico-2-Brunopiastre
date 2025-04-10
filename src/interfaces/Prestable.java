package interfaces;

import modelo.Usuario;

public interface Prestable {
    boolean estaDisponible();
    void prestar(Usuario usuario);
    void devolver();
}
