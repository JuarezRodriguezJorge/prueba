package hospital.pacientes;

/**
 * Paciente que requiere medicina general.
 * Su especialidad de atención siempre es "General".
 *
 * @author Grupo 7
 * @version 2.0
 */
public class PacienteGeneral extends Paciente {

    private String motivoConsulta;

    /**
     * Crea un paciente general.
     *
     * @param nombre nombre del paciente
     * @param apellidos apellidos del paciente
     * @param motivoConsulta motivo por el que pide consulta
     */
    public PacienteGeneral(String nombre, String apellidos, String motivoConsulta) {
        super(nombre, apellidos, "General");
        this.motivoConsulta = textoValido(motivoConsulta, "motivo de consulta");
    }

    /** @return motivo de consulta */
    public String getMotivoConsulta() {
        return this.motivoConsulta;
    }

    /**
     * Cambia el motivo de consulta (puede cambiar en cada visita).
     *
     * @param motivoConsulta nuevo motivo
     */
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = textoValido(motivoConsulta, "motivo de consulta");
    }

    /** Comportamiento propio: muestra el motivo de consulta. */
    public void verMotivo() {
        System.out.println("Motivo de consulta de " + this.getNombre() + ": " + this.motivoConsulta);
    }

    /** Sobrescribe el método abstracto de Paciente. */
    public void solicitarConsulta() {
        System.out.println("El paciente " + this.getNombre() + " solicita consulta de medicina general. Motivo: "
                + this.motivoConsulta);
    }

    /** @return tipo de paciente */
    public String getTipo() {
        return "Paciente general";
    }
}
