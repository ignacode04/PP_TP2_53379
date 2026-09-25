package certificacion;

import modelo.Estudiante;

public interface Certificable {
    // Texto constante que indica la institución emisora
    String ENTIDAD_EMISORA = "Universidad Tecnológica Nacional - UTN";

    // Método que cada clase certificable deberá implementar a su manera
    String generarCertificado(Estudiante estudiante);
}