import modelo.*;
import actividades.*;
import hilos.EnvioTicketsThread;
import excepciones.CupoExcedidoException;

public class App {
    public static void main(String[] args) {
        System.out.println("=== INTEGRACIÓN FINAL TP2: GESTIÓN DE EVENTOS UNIVERSITARIOS ===");

        // 1. Crear Evento y Asignar Sala
        EventoUniversitario evento = new EventoUniversitario("EV-FINAL", "Expo Universidad 2026", 2500.0, false);
        Sala salaPrincipal = new Sala(501, "Salón de Actos");
        evento.asignarSala(salaPrincipal);

        // 2. Crear Actividades
        Charla charla = new Charla(1, "Charla de Inteligencia Artificial", 10, "Dr. Pérez");
        Taller taller = new Taller(2, "Taller de Desarrollo Java", 10, true);
        Curso curso = new Curso(3, "Curso de Algoritmos Avanzados", 10, 30);

        evento.agregarActividad(charla);
        evento.agregarActividad(taller);
        evento.agregarActividad(curso);

        // 3. Crear Estudiantes e Inscribirlos
        Estudiante e1 = new Estudiante("53379", "Juan Pérez");
        Estudiante e2 = new Estudiante("53380", "María López");
        Estudiante e3 = new Estudiante("53381", "Carlos Rodríguez");

        try {
            charla.inscribir(e1);
            taller.inscribir(e2);
            curso.inscribir(e3);
        } catch (CupoExcedidoException e) {
            System.err.println("Error de cupo: " + e.getMessage());
        }

        // 4. EJECUCIÓN DEL HILO EN SEGUNDO PLANO (Ejercicio 4)
        EnvioTicketsThread hiloEnvio = new EnvioTicketsThread(evento);

        // Con start() el hilo arranca a ejecutarse en paralelo sin bloquear el main
        hiloEnvio.start();

        // 5. El programa principal continúa su marcha de inmediato mientras el hilo trabaja
        System.out.println("\n[PROGRAMA PRINCIPAL] Continuando operaciones sin esperar al hilo...");
        evento.mostrarDatos();
        System.out.println("[PROGRAMA PRINCIPAL] Fin del hilo principal. El sistema sigue procesando los tickets...");
    }
}