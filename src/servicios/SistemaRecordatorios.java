package servicios;

import modelo.Prestamo;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SistemaRecordatorios {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AlertaVencimiento alerta = new AlertaVencimiento();

    private static final long INTERVALO_VERIFICACION = 24;

    public void iniciar(List<Prestamo> prestamos) {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("\n🕒 Recordatorio automático: verificando vencimientos...");
            alerta.verificarVencimientos(prestamos);
        }, 0, INTERVALO_VERIFICACION, TimeUnit.HOURS);
    }

    public void detener() {
        scheduler.shutdownNow();
        System.out.println("🛑 Sistema de recordatorios detenido.");
    }
}
