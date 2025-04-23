package servicios;

import java.util.ArrayList;
import java.util.List;

public class HistorialAlertas {
    private static final List<String> historial = new ArrayList<>();

    public static void registrar(String mensaje) {
        historial.add(mensaje);
    }

    public static void mostrar() {
        System.out.println("\n📜 Historial de alertas:");
        if (historial.isEmpty()) {
            System.out.println("No hay alertas registradas.");
        } else {
            for (String alerta : historial) {
                System.out.println("- " + alerta);
            }
        }
    }

    public static void limpiar() {
        historial.clear();
    }
}
