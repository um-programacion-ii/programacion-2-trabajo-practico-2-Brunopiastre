package gestores;

import modelo.RecursoBase;

import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private final List<RecursoBase> recursos = new ArrayList<>();

    public void agregar(RecursoBase recurso) {
        recursos.add(recurso);
    }

    public List<RecursoBase> listar() {
        return recursos;
    }

    public RecursoBase buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }
}