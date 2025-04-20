package gestores;

import modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private final Map<String, Usuario> usuarios = new HashMap<>();

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.get(id);
    }

    public boolean existeUsuario(String id) {
        return usuarios.containsKey(id);
    }

    public Map<String, Usuario> getTodos() {
        return usuarios;
    }
}
