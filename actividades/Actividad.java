package actividades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Estudiante;
import modelo.Inscripcion;
import excepciones.CupoExcedidoException;

public abstract class Actividad implements Serializable {
    protected int id;
    protected String titulo;
    protected int cupoMaximo;
    public static final int CUPO_MINIMO = 5;

    protected List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (this.inscripciones.size() >= this.cupoMaximo) {
            throw new CupoExcedidoException("No se puede inscribir a " + estudiante.getNombre() +
                    ". El cupo máximo (" + this.cupoMaximo + ") para la actividad '" + this.titulo + "' ha sido alcanzado.");
        }

        Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
        this.inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}
