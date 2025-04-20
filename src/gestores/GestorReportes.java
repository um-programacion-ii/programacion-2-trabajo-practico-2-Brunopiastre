package gestores;

import modelo.RecursoBase;
import modelo.Usuario;
import modelo.Prestamo;

import java.util.*;
import java.util.stream.Collectors;

public class GestorReportes {
    private final List<Prestamo> prestamos;

    public GestorReportes(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Map.Entry<RecursoBase, Long>> recursosMasPrestados() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<RecursoBase, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Usuario, Long>> usuariosMasActivos() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public Map<String, Long> prestamosPorTipo() {
        return prestamos.stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getTipo().name(), Collectors.counting()));
    }
}
