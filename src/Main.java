import modelo.Libro;
import modelo.Usuario;
import gestores.GestorRecursos;

public class Main {
    public static void main(String[] args) {
        GestorRecursos gestor = new GestorRecursos();

        Usuario usuario = new Usuario("U1", "Ana", "ana@email.com");
        Libro libro = new Libro("L1", "El Principito", "Saint-Exupéry");

        gestor.agregar(libro);

        System.out.println("📚 Recursos disponibles:");
        gestor.listar().forEach(System.out::println);

        System.out.println("\n🔐 Prestando libro...");
        libro.prestar(usuario);

        System.out.println("✅ Estado actual:");
        gestor.listar().forEach(System.out::println);
    }
}
