import gestores.*;
import gestores.SistemaPrestamosConcurrente;
import interfaces.Notificable;
import modelo.*;
import servicios.AlertaVencimiento;
import servicios.NotificadorConsola;
import servicios.ServicioNotificaciones;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        // Inicialización
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        SistemaReservas sistemaReservas = new SistemaReservas();
        Notificable canal = new NotificadorConsola();
        ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones(canal);
        SistemaPrestamosConcurrente sistemaPrestamos = new SistemaPrestamosConcurrente(sistemaReservas, servicioNotificaciones);
        GestorReportes gestorReportes = null;

        boolean salir = false;

        while (!salir) {
            System.out.println("\n========= MENÚ =========");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Registrar recurso");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Devolver recurso");
            System.out.println("5. Ver reportes");
            System.out.println("6. Ver alertas de vencimiento");
            System.out.println("0. Salir");
            System.out.print("Seleccioná una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("ID de usuario: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    gestorUsuarios.registrarUsuario(new Usuario(id, nombre, email));
                    System.out.println("✅ Usuario registrado.");
                    break;

                case "2":
                    System.out.print("Título del recurso: ");
                    String titulo = scanner.nextLine();
                    System.out.print("ID del recurso: ");
                    String idRecurso = scanner.nextLine();
                    System.out.print("Tipo (LIBRO, REVISTA, AUDIOLIBRO): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    RecursoBase recurso = switch (tipo) {
                        case "REVISTA" -> new Revista(titulo, idRecurso);
                        case "AUDIOLIBRO" -> new Audiolibro(titulo, idRecurso);
                        default -> new Libro(titulo, idRecurso);
                    };
                    gestorRecursos.registrarRecurso(recurso);
                    System.out.println("✅ Recurso registrado.");
                    break;

                case "3":
                    System.out.print("ID del usuario: ");
                    String idUsuario = scanner.nextLine();
                    System.out.print("ID del recurso: ");
                    String idRec = scanner.nextLine();
                    Usuario u = gestorUsuarios.buscarUsuarioPorId(idUsuario);
                    RecursoBase r = gestorRecursos.buscarRecursoPorId(idRec);
                    if (u != null && r != null) {
                        sistemaPrestamos.agregarSolicitud(new SolicitudPrestamo(u, r));
                        System.out.println("✅ Solicitud enviada.");
                    } else {
                        System.out.println("❌ Usuario o recurso no encontrado.");
                    }
                    Thread.sleep(1000);
                    break;

                case "4":
                    System.out.print("ID del recurso a devolver: ");
                    String idDevolver = scanner.nextLine();
                    sistemaPrestamos.devolverRecurso(idDevolver);
                    break;

                case "5":
                    gestorReportes = new GestorReportes(sistemaPrestamos.getTodos());
                    gestorReportes.mostrarReporteRecursosMasPrestados();
                    gestorReportes.mostrarReporteUsuariosMasActivos();
                    gestorReportes.mostrarReportePorTipo();
                    gestorReportes.exportarRecursosMasPrestados("reporte_recursos.txt");
                    break;

                case "6":
                    AlertaVencimiento alerta = new AlertaVencimiento();
                    alerta.verificarVencimientos(sistemaPrestamos.getTodos());
                    break;

                case "0":
                    salir = true;
                    sistemaPrestamos.apagarProcesador();
                    System.out.println("👋 Cerrando sistema...");
                    break;

                default:
                    System.out.println("❌ Opción inválida.");
                    break;
            }
        }
        scanner.close();
    }
}
