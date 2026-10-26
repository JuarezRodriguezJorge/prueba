package hospital.interfaces;

import hospital.pacientes.Paciente;

/**
 * Contrato que cumple todo el personal que atiende pacientes
 * (médicos y enfermeros). Define QUÉ puede hacer, no CÓMO lo hace.
 *
 * @author Grupo 7
 * @version 2.0
 */
public interface AtencionMedica {

    /**
     * Aplica o receta un tratamiento a un paciente asignado.
     *
     * @param p paciente que recibirá el tratamiento
     * @return true si el tratamiento se aplicó, false si hubo un error
     */
    boolean darTratamiento(Paciente p);

    /**
     * Muestra los pacientes asignados en orden alfabético descendente.
     */
    void verListaPacientes();

    /**
     * Devuelve el nombre del profesional.
     *
     * @return nombre del profesional
     */
    String getNombre();
}
