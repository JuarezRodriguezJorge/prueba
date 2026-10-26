package hospital.personal;

import hospital.interfaces.AtencionMedica;
import hospital.pacientes.Paciente;
import hospital.sistema.Sistema;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Clase base abstracta de los médicos.
 * Guarda hasta 10 pacientes y atiende en consulta a uno solo a la vez.
 * Cada tipo de médico decide qué pacientes puede atender.
 *
 * @author Grupo 7
 * @version 2.0
 */
public abstract class Medico implements AtencionMedica {

    /** Máximo de pacientes asignados a un médico. */
    public static final int LIMITE_PACIENTES = 10;

    private String nombre;
    private String cedula;
    private String especialidad;
    private ArrayList<Paciente> listaPacientes;
    private Paciente pacienteEnConsulta;

    /**
     * Crea un médico sin pacientes.
     *
     * @param nombre nombre del médico
     * @param cedula cédula profesional (no se puede cambiar después)
     * @param especialidad especialidad del médico
     */
    public Medico(String nombre, String cedula, String especialidad) {
        this.nombre = textoValido(nombre, "nombre");
        this.cedula = textoValido(cedula, "cédula");
        this.especialidad = textoValido(especialidad, "especialidad");
        this.listaPacientes = new ArrayList<Paciente>();
        this.pacienteEnConsulta = null;
    }

    /**
     * Valida que un texto no sea nulo ni vacío.
     *
     * @param texto texto a validar
     * @param campo nombre del campo, solo para el mensaje de error
     * @return el texto sin espacios sobrantes, o "Sin dato" si era inválido
     */
    protected static String textoValido(String texto, String campo) {
        if (texto == null || texto.trim().length() == 0) {
            System.out.println("Error: el campo " + campo + " no puede estar vacío. Se guardará \"Sin dato\".");
            return "Sin dato";
        }
        return texto.trim();
    }

    /** @return nombre del médico */
    public String getNombre() {
        return this.nombre;
    }

    /** @return cédula profesional */
    public String getCedula() {
        return this.cedula;
    }

    /** @return especialidad del médico */
    public String getEspecialidad() {
        return this.especialidad;
    }

    /** @return cantidad de pacientes asignados */
    public int getNoPacientes() {
        return this.listaPacientes.size();
    }

    /** @return paciente en consulta, o null si está libre */
    public Paciente getPacienteEnConsulta() {
        return this.pacienteEnConsulta;
    }

    /**
     * Indica si este tipo de médico puede atender a un paciente.
     *
     * @param p paciente a revisar
     * @return true si puede atenderlo
     */
    public abstract boolean puedeAtender(Paciente p);

    /** @return nombre del tipo de médico */
    public abstract String getTipo();

    /** Imprime el mensaje de registro del médico. */
    public void registroEnSistema() {
        System.out.println("Médico " + this.nombre + " registrado (" + this.getTipo() + "). Especialidad: "
                + this.especialidad);
    }

    /**
     * Revisa si el paciente ya está en la lista de este médico.
     *
     * @param p paciente a buscar
     * @return true si ya está asignado
     */
    public boolean tienePaciente(Paciente p) {
        return this.listaPacientes.indexOf(p) != -1;
    }

    /**
     * Agrega un paciente a la lista si cumple todas las restricciones.
     *
     * @param p paciente a agregar
     * @return true si se agregó
     */
    public boolean agregarPaciente(Paciente p) {
        if (p == null) {
            System.out.println("Error: el paciente no existe.");
            return false;
        }
        if (!this.puedeAtender(p)) {
            System.out.println("Error: La especialidad no coincide (" + this.getTipo() + " de "
                    + this.especialidad + "; el paciente requiere " + p.getEspecialidadAtencion() + ").");
            return false;
        }
        if (this.tienePaciente(p)) {
            System.out.println("Error: El paciente ya está asignado a este médico.");
            return false;
        }
        if (this.listaPacientes.size() >= LIMITE_PACIENTES) {
            System.out.println("Error: El médico ya tiene " + LIMITE_PACIENTES + " pacientes.");
            return false;
        }
        this.listaPacientes.add(p);
        System.out.println("Paciente asignado al médico " + this.nombre);
        return true;
    }

    /**
     * El médico pide al sistema que le asigne al paciente.
     *
     * @param sistema sistema del hospital
     * @param p paciente solicitado
     * @return true si al terminar el paciente está en su lista
     */
    public boolean solicitarPaciente(Sistema sistema, Paciente p) {
        System.out.println("El médico " + this.nombre + " solicita al paciente " + p.getNombre());
        if (!this.tienePaciente(p)) {
            sistema.asignarPaciente(this, p);
        }
        return this.tienePaciente(p);
    }

    /**
     * Da consulta a un paciente asignado, solo si el médico está libre.
     *
     * @param p paciente a atender
     * @return true si la consulta comenzó
     */
    public boolean darConsulta(Paciente p) {
        if (!this.tienePaciente(p)) {
            System.out.println("Error: el paciente " + p.getNombre() + " no está asignado al médico "
                    + this.nombre + ".");
            return false;
        }
        if (this.pacienteEnConsulta != null) {
            System.out.println("Error: el médico " + this.nombre + " ya está dando consulta a "
                    + this.pacienteEnConsulta.getNombre() + ". Solo puede atender a un paciente a la vez.");
            return false;
        }
        this.pacienteEnConsulta = p;
        p.setEstado(Paciente.CONSULTA);
        System.out.println("El médico " + this.nombre + " está dando consulta a " + p.getNombre());
        return true;
    }

    /**
     * Receta tratamiento a un paciente asignado y libera la consulta si era su paciente.
     *
     * @param p paciente que recibe el tratamiento
     * @return true si se recetó el tratamiento
     */
    public boolean darTratamiento(Paciente p) {
        if (!this.tienePaciente(p)) {
            System.out.println("Error: el paciente " + p.getNombre() + " no está asignado al médico "
                    + this.nombre + ".");
            return false;
        }
        p.setEstado(Paciente.TRATAMIENTO);
        if (this.pacienteEnConsulta == p) {
            this.pacienteEnConsulta = null;
        }
        System.out.println("El médico " + this.nombre + " receta tratamiento a " + p.getNombre());
        return true;
    }

    /** Muestra los pacientes en orden alfabético descendente (apellidos, nombre y especialidad). */
    public void verListaPacientes() {
        TreeSet<String> ordenadas = new TreeSet<String>();
        for (int i = 0; i < this.listaPacientes.size(); i++) {
            Paciente p = this.listaPacientes.get(i);
            ordenadas.add(p.getApellidos() + " " + p.getNombre() + " - " + p.getEspecialidadAtencion());
        }
        String[] arregloOrdenado = ordenadas.toArray(new String[0]);
        System.out.println("\nLista de pacientes de " + this.nombre + " (Orden Descendente):");
        if (arregloOrdenado.length == 0) {
            System.out.println("  (sin pacientes asignados)");
        }
        for (int i = arregloOrdenado.length - 1; i >= 0; i--) {
            System.out.println(arregloOrdenado[i]);
        }
    }
}
