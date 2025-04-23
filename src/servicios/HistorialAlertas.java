package servicios;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class HistorialAlertas {
    private static final List<String> historial = Collections.synchronizedList(new ArrayList<>());

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
