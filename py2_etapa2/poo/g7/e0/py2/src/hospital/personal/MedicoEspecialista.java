package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteEspecialidad;

/**
 * Médico especialista. Solo atiende pacientes de su especialidad.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class MedicoEspecialista extends Medico {

    private String certificacion;

    /**
     * Crea un médico especialista.
     *
     * @param nombre nombre del médico
     * @param cedula cédula profesional
     * @param especialidad especialidad que atiende
     * @param certificacion certificación de su especialidad
     */
    public MedicoEspecialista(String nombre, String cedula, String especialidad, String certificacion) {
        super(nombre, cedula, especialidad);
        this.certificacion = textoValido(certificacion, "certificación");
    }

    /** @return certificación del especialista */
    public String getCertificacion() {
        return this.certificacion;
    }

    /** Comportamiento propio: muestra la certificación. */
    public void verCertificacion() {
        System.out.println("El médico " + this.getNombre() + " tiene la certificación: " + this.certificacion);
    }

    /** Solo atiende pacientes de especialidad cuya especialidad coincide con la suya. */
    public boolean puedeAtender(Paciente p) {
        return p instanceof PacienteEspecialidad
                && this.getEspecialidad().equalsIgnoreCase(p.getEspecialidadAtencion());
    }

    /** Sobrescribe darConsulta: conserva las reglas de Medico y agrega su propio mensaje. */
    public boolean darConsulta(Paciente p) {
        boolean ok = super.darConsulta(p);
        if (ok) {
            System.out.println("  Consulta especializada de " + this.getEspecialidad()
                    + ": diagnóstico detallado y plan de tratamiento.");
        }
        return ok;
    }

    /** @return tipo de médico */
    public String getTipo() {
        return "Médico especialista";
    }
}
