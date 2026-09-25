import modelo.*;
import actividades.*;
import excepciones.CupoExcedidoException;

public class Main {
    public static void main(String[] args) {

        EventoUniversitario evento = new EventoUniversitario("EVT-01", "Congreso Tecnológico", 1500.0, false);
        Sala sala1 = new Sala(101, "Aula Magna");
        evento.asignarSala(sala1);

        Charla charla = new Charla(1, "Charla de IA en Ingeniería", 30, "Dr. Pérez");
        Taller taller = new Taller(2, "Taller práctico de Java", 20, true);

        evento.agregarActividad(charla);
        evento.agregarActividad(taller);

        Estudiante e1 = new Estudiante("53379", "Juan Pérez");
        Estudiante e2 = new Estudiante("53380", "Maria Gomez");
        Estudiante e3 = new Estudiante("53381", "Carlos Lopez");

        try {
            charla.inscribir(e1);
            charla.inscribir(e2);

            taller.inscribir(e2);
            taller.inscribir(e3);
        } catch (CupoExcedidoException e) {
            System.err.println("Error durante la inscripción: " + e.getMessage());
        }

        evento.mostrarDatos();
        System.out.println("Total de eventos creados: " + EventoUniversitario.getCantidadEventos());
    }
}