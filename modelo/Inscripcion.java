package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket; // Atributo para el ticket

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "Confirmada";
        // Generamos un ticket único usando la clase interna anidada
        this.ticket = new TicketDeAcceso();
    }

    // --- CLASE ANIDADA (INNER CLASS) ---
    public class TicketDeAcceso implements Serializable {
        private final String codigoTicket;

        public TicketDeAcceso() {
            // Genera un código único al azar, por ejemplo: TCK-A1B2C3
            this.codigoTicket = "TCK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        }

        public void enviarTicket() {
            System.out.println("  [ENVIANDO TICKET] -> Enviando acceso " + codigoTicket +
                    " al estudiante: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")");
        }

        public String getCodigoTicket() { return codigoTicket; }
    }

    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }
    public TicketDeAcceso getTicket() { return ticket; }
}