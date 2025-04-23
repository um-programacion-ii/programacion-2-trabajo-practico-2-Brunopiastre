package servicios;

import gestores.GestorReportes;
import modelo.Prestamo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeneradorReportesAsincrono {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void generar(List<Prestamo> prestamos) {
        System.out.println("📊 Generando reportes en segundo plano...");

        executor.submit(() -> {
            GestorReportes reportes = new GestorReportes(prestamos);
            reportes.mostrarReporteRecursosMasPrestados();
            reportes.mostrarReporteUsuariosMasActivos();
            reportes.mostrarReportePorTipo();
            reportes.exportarRecursosMasPrestados("reporte_recursos.txt");
            System.out.println("✅ Reportes generados.");
        });
    }

    public void apagar() {
        executor.shutdownNow();
    }
}
