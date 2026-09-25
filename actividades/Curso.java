package actividades;

import certificacion.Certificable;
import modelo.Estudiante;

// 'Curso' hereda de 'Actividad' e implementa la interfaz 'Certificable'
public class Curso extends Actividad implements Certificable {
    private int horas;

    public Curso(int id, String titulo, int cupoMaximo, int horas) {
        super(id, titulo, cupoMaximo); // Llama al constructor de la clase madre (Actividad)
        this.horas = horas;
    }

    @Override
    public double calcularCostoMateriales() {
        // Un cálculo estimado de costo de materiales según las horas
        return this.horas * 150.0;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    // Método obligatorio exigido por la interfaz Certificable
    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "CERTIFICADO DE APROBACIÓN - " + ENTIDAD_EMISORA + "\n" +
                "Se otorga el presente certificado a: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "Por haber completado con éxito el Curso: '" + getTitulo() + "' con una carga horaria de " + horas + " hs.";
    }

    public int getHoras() { return horas; }
}