package actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 500.0 : 200.0;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    // Método obligatorio exigido por la interfaz Certificable
    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "CERTIFICADO DE ASISTENCIA - " + ENTIDAD_EMISORA + "\n" +
                "Se otorga el presente certificado a: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "Por su participación activa en el Taller: '" + getTitulo() + "'.";
    }

    public boolean isRequiereNotebook() { return requiereNotebook; }
}