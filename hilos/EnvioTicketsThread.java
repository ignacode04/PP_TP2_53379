package hilos;

import modelo.EventoUniversitario;
import modelo.Inscripcion;
import actividades.Actividad;

// Hereda de Thread para ejecutarse en segundo plano
public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("\n>>> [HILO SECUNDARIO] Iniciando envío masivo de tickets en segundo plano...");

        for (Actividad actividad : evento.getActividades()) {
            for (Inscripcion inscripcion : actividad.getInscripciones()) {
                // Simula una pequeña demora de 1 segundo entre envíos
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Hilo interrumpido: " + e.getMessage());
                }

                // Enviar el ticket usando la clase anidada
                if (inscripcion.getTicket() != null) {
                    inscripcion.getTicket().enviarTicket();
                }
            }
        }

        System.out.println(">>> [HILO SECUNDARIO] Envío masivo de tickets COMPLETADO con éxito. <<<\n");
    }
}