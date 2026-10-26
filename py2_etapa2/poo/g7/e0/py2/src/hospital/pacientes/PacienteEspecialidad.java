package hospital.pacientes;

/**
 * Paciente que requiere atención de una especialidad médica.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class PacienteEspecialidad extends Paciente {

    private String diagnosticoPrevio;

    /**
     * Crea un paciente de especialidad.
     *
     * @param nombre nombre del paciente
     * @param apellidos apellidos del paciente
     * @param especialidadAtencion especialidad que requiere
     * @param diagnosticoPrevio diagnóstico con el que llega
     */
    public PacienteEspecialidad(String nombre, String apellidos, String especialidadAtencion,
            String diagnosticoPrevio) {
        super(nombre, apellidos, especialidadAtencion);
        this.diagnosticoPrevio = textoValido(diagnosticoPrevio, "diagnóstico previo");
    }

    /** @return diagnóstico previo */
    public String getDiagnosticoPrevio() {
        return this.diagnosticoPrevio;
    }

    /** Comportamiento propio: muestra el diagnóstico previo. */
    public void verDiagnostico() {
        System.out.println("Diagnóstico previo de " + this.getNombre() + ": " + this.diagnosticoPrevio);
    }

    /** Sobrescribe el método abstracto de Paciente. */
    public void solicitarConsulta() {
        System.out.println("El paciente " + this.getNombre() + " solicita consulta de la especialidad "
                + this.getEspecialidadAtencion() + ". Diagnóstico previo: " + this.diagnosticoPrevio);
    }

    /** @return tipo de paciente */
    public String getTipo() {
        return "Paciente de especialidad";
    }
}
