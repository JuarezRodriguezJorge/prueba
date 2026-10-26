package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;

/**
 * Enfermero general. Solo atiende pacientes generales.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class EnfermeroGeneral extends Enfermero {

    private String turno;

    /**
     * Crea un enfermero general. Su especialidad siempre es "General".
     *
     * @param nombre nombre del enfermero
     * @param cedula cédula profesional
     * @param turno turno de trabajo
     */
    public EnfermeroGeneral(String nombre, String cedula, String turno) {
        super(nombre, cedula, "General");
        this.turno = textoValido(turno, "turno");
    }

    /** @return turno de trabajo */
    public String getTurno() {
        return this.turno;
    }

    /**
     * Cambia el turno (puede reasignarse).
     *
     * @param turno nuevo turno
     */
    public void setTurno(String turno) {
        this.turno = textoValido(turno, "turno");
    }

    /** Comportamiento propio: muestra el turno. */
    public void verTurno() {
        System.out.println("El enfermero " + this.getNombre() + " trabaja en el turno " + this.turno);
    }

    /** Solo atiende pacientes generales. */
    public boolean puedeAtender(Paciente p) {
        return p instanceof PacienteGeneral;
    }

    /** Sobrescribe darTratamiento: conserva las reglas de Enfermero y agrega su propio mensaje. */
    public boolean darTratamiento(Paciente p) {
        boolean ok = super.darTratamiento(p);
        if (ok) {
            System.out.println("  Cuidados generales aplicados (turno " + this.turno + ").");
        }
        return ok;
    }

    /** @return tipo de enfermero */
    public String getTipo() {
        return "Enfermero general";
    }
}
