### Documentación del Sistema
1. **Cómo funciona el sistema**:

El sistema de gestión de biblioteca digital está diseñado siguiendo los principios SOLID y orientado a objetos. Está dividido en paquetes organizados de la siguiente manera:

- `modelo`: contiene las entidades principales del sistema, como `Usuario`, `Libro`, `Revista`, `Audiolibro`, `Prestamo`, etc.
- `gestores`: contiene las clases que administran la lógica de negocio, como `GestorUsuarios`, `GestorRecursos` y `SistemaPrestamos`.
- `servicios`: incluye clases auxiliares para alertas y notificaciones (`AlertaVencimiento`, `ServicioNotificaciones`, etc.)
- `interfaces`: define contratos para las clases que cumplen funciones específicas (`Prestable`, `RecursoDigital`, `Notificable`).
- `Main.java`: archivo principal donde se prueba el sistema con ejemplos funcionales.

### Flujo general del sistema:

1. Se registran usuarios usando `GestorUsuarios`.
2. Se registran recursos digitales (`Libro`, `Revista`, `Audiolibro`) usando `GestorRecursos`.
3. Se realiza un préstamo desde `SistemaPrestamos`, validando que el usuario esté registrado y el recurso esté disponible.
4. Se puede devolver un recurso prestado usando su ID.
5. Se generan reportes de uso y alertas desde clases específicas (`GestorReportes`, `AlertaVencimiento`).

---

2. **Cómo ponerlo en funcionamiento**:

### Requisitos Previos

- Tener instalado Java 21 o superior
- Tener Git (para clonar el repositorio si se desea)
- (Opcional) Usar un IDE como IntelliJ IDEA o VS Code

---

### Compilación y Ejecución desde la Terminal (Linux/macOS)

1. Abrí una terminal en la raíz del proyecto (donde está la carpeta `src/`)
2. Compilá los archivos Java:

```bash
javac -d out src/**/*.java
```
**Ejecutá el archivo principal Main.java**:
 
    ###java -cp out Main

Compilación y Ejecución desde IntelliJ IDEA

    Abrí el proyecto desde IntelliJ

    Asegurate de tener configurado el SDK de Java 21+

    Hacé clic derecho sobre Main.java y seleccioná "Run 'Main'"


3. **Cómo probar cada aspecto desarrollado**:

### ✔️ Flujo completo de prueba funcional

El archivo `Main.java` incluye una simulación funcional del sistema. Este flujo prueba las siguientes funcionalidades:

1. **Registrar un usuario**  
   Se crea un usuario con ID, nombre y email y se registra mediante `GestorUsuarios`.

2. **Registrar recursos digitales**  
   Se crean y registran tres tipos de recurso (`Libro`, `Revista`, `Audiolibro`) mediante `GestorRecursos`.

3. **Realizar un préstamo**  
   Se busca un recurso y un usuario registrados. Si el recurso está disponible, se realiza el préstamo usando `SistemaPrestamos`.

4. **Ver préstamos realizados**  
   Se imprime una lista de préstamos registrados mostrando qué usuario tomó qué recurso.

5. **Devolver un recurso**  
   Se devuelve el recurso usando su ID y se verifica que el estado del recurso vuelva a `DISPONIBLE`.
6. **Reservas de recursos**
   Si un recurso ya está prestado, el sistema permite que otros usuarios lo reserven. Las reservas se encolan y se muestran en orden.

7. **Reasignación automática al devolver**
   Al devolver un recurso, si hay reservas pendientes, se entrega automáticamente al siguiente usuario en la cola.

8. **Notificaciones**
   El sistema muestra en consola una notificación cuando se reasigna un recurso a un usuario que lo tenía reservado.

9. **Alertas con niveles de urgencia**
   - 🔔 El recurso vence MAÑANA
   - ⚠️ El recurso vence HOY
   - ⛔ El recurso ya Venció

10. **Procesamiento concurrente de préstamos**
    Se utilizan `ExecutorService` y `BlockingQueue` para procesar múltiples solicitudes de préstamo en segundo plano.

11. **Visualización extendida de reportes**
    Los reportes se muestran en formato de tabla en consola e incluyen:
   - Recursos más prestados
   - Usuarios más activos
   - Préstamos por tipo de recurso

12. **Exportación de reportes**
    Los reportes también se exportan automáticamente a un archivo `reporte_recursos.txt`.

---

---

### 🖥️ Ejemplo de salida esperada en consola

🚀 Enviando solicitudes de préstamo concurrentes... ✅ [HILO] Préstamo procesado: Bruno Piastrellini → El Principito 📌 Reserva registrada para el recurso 'El Principito' por el usuario Juliana Piastrellini 📌 Reserva registrada para el recurso 'El Principito' por el usuario Lalo Landa

🛎️ Verificando vencimientos: 🔔 ALERTA: El recurso 'El Principito' vence MAÑANA para el usuario Bruno Piastrellini

🔁 Devolviendo recurso... ✅ Recurso devuelto correctamente. 🔔 NOTIFICACIÓN: El recurso 'El Principito' fue entregado automáticamente a Juliana Piastrellini

📋 Lista de préstamos: Bruno Piastrellini → El Principito (vence: 2025-04-21)

📊 Recursos más prestados: El Principito → 1 préstamos

👤 Usuarios más activos: Bruno Piastrellini → 1 préstamos

📚 Préstamos por tipo de recurso: LIBRO → 1

📁 Reporte exportado exitosamente a reporte_recursos.txt