package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;

/**
 * Médico de medicina general. Solo atiende pacientes generales.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class MedicoGeneral extends Medico {

    private String consultorio;

    /**
     * Crea un médico general. Su especialidad siempre es "General".
     *
     * @param nombre nombre del médico
     * @param cedula cédula profesional
     * @param consultorio consultorio donde atiende
     */
    public MedicoGeneral(String nombre, String cedula, String consultorio) {
        super(nombre, cedula, "General");
        this.consultorio = textoValido(consultorio, "consultorio");
    }

    /** @return consultorio asignado */
    public String getConsultorio() {
        return this.consultorio;
    }

    /**
     * Cambia el consultorio (puede reasignarse).
     *
     * @param consultorio nuevo consultorio
     */
    public void setConsultorio(String consultorio) {
        this.consultorio = textoValido(consultorio, "consultorio");
    }

    /** Comportamiento propio: muestra el consultorio. */
    public void verConsultorio() {
        System.out.println("El médico " + this.getNombre() + " atiende en el consultorio " + this.consultorio);
    }

    /** Solo atiende pacientes generales. */
    public boolean puedeAtender(Paciente p) {
        return p instanceof PacienteGeneral;
    }

    /** Sobrescribe darConsulta: conserva las reglas de Medico y agrega su propio mensaje. */
    public boolean darConsulta(Paciente p) {
        boolean ok = super.darConsulta(p);
        if (ok) {
            System.out.println("  Consulta de medicina general en el consultorio " + this.consultorio
                    + ": revisión integral del paciente.");
        }
        return ok;
    }

    /** @return tipo de médico */
    public String getTipo() {
        return "Médico general";
    }
}
