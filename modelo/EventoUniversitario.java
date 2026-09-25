package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import actividades.Actividad;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }




    @SuppressWarnings("unchecked")
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad act : actividades) {
            // isInstance verifica si la actividad 'act' pertenece a la clase pasada por parámetro
            if (tipo.isInstance(act)) {
                resultado.add((T) act);
            }
        }
        return resultado;
    }

    public double calcularCostoMateriales(List<? extends Actividad> listaActividades) {
        double costoTotal = 0.0;
        for (Actividad act : listaActividades) {
            costoTotal += act.calcularCostoMateriales();
        }
        return costoTotal;
    }


    public void mostrarDatos() {
        System.out.println("--- DATOS DEL EVENTO ---");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Costo Base: $" + costoBase);
        System.out.println("Es Gratuito: " + (gratuito ? "Sí" : "No"));
        if (sala != null) {
            System.out.println("Sala Asignada: " + sala.getNombre());
        }
        System.out.println("Cantidad de Actividades: " + actividades.size());
    }

    public boolean persistirEvento() {
        String nombreArchivo = "evento_" + this.id + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            System.err.println("Error al persistir el evento: " + e.getMessage());
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) {
        String nombreArchivo = "evento_" + id + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (EventoUniversitario) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error al leer el evento: " + e.getMessage());
            return null;
        }
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Actividad> getActividades() { return actividades; }
    public static int getCantidadEventos() { return cantidadEventos; }
}