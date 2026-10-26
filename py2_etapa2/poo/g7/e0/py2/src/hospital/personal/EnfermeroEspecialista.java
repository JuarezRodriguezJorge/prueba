package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteEspecialidad;

/**
 * Enfermero especialista. Solo atiende pacientes de su especialidad.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class EnfermeroEspecialista extends Enfermero {

    private String certificacion;

    /**
     * Crea un enfermero especialista.
     *
     * @param nombre nombre del enfermero
     * @param cedula cédula profesional
     * @param especialidad especialidad que atiende
     * @param certificacion certificación de su especialidad
     */
    public EnfermeroEspecialista(String nombre, String cedula, String especialidad, String certificacion) {
        super(nombre, cedula, especialidad);
        this.certificacion = textoValido(certificacion, "certificación");
    }

    /** @return certificación del especialista */
    public String getCertificacion() {
        return this.certificacion;
    }

    /** Comportamiento propio: muestra la certificación. */
    public void verCertificacion() {
        System.out.println("El enfermero " + this.getNombre() + " tiene la certificación: " + this.certificacion);
    }

    /** Solo atiende pacientes de especialidad cuya especialidad coincide con la suya. */
    public boolean puedeAtender(Paciente p) {
        return p instanceof PacienteEspecialidad
                && this.getEspecialidad().equalsIgnoreCase(p.getEspecialidadAtencion());
    }

    /** Sobrescribe darTratamiento: conserva las reglas de Enfermero y agrega su propio mensaje. */
    public boolean darTratamiento(Paciente p) {
        boolean ok = super.darTratamiento(p);
        if (ok) {
            System.out.println("  Cuidados especializados de " + this.getEspecialidad() + " aplicados.");
        }
        return ok;
    }

    /** @return tipo de enfermero */
    public String getTipo() {
        return "Enfermero especialista";
    }
}
