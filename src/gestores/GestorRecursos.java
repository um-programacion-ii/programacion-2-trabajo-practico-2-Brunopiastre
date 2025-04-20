package gestores;

import modelo.RecursoBase;

import java.util.HashMap;
import java.util.Map;

public class GestorRecursos {
    private final Map<String, RecursoBase> recursos = new HashMap<>();

    public void registrarRecurso(RecursoBase recurso) {
        recursos.put(recurso.getIdentificador(), recurso);
    }

    public RecursoBase buscarRecursoPorId(String id) {
        return recursos.get(id);
    }

    public Map<String, RecursoBase> getTodos() {
        return recursos;
    }
}
