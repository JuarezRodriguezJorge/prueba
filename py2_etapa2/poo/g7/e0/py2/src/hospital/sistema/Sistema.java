package hospital.sistema;

import hospital.interfaces.AtencionMedica;
import hospital.pacientes.Paciente;
import hospital.personal.Enfermero;
import hospital.personal.Medico;
import java.util.ArrayList;

/**
 * Clase controladora del hospital: guarda los registros y asigna pacientes.
 * Los registros se guardan con la clase base, así que cada lista
 * acepta objetos de todas las subclases (polimorfismo).
 *
 * @author Grupo 7
 * @version 2.0
 */
public class Sistema {

    private String nombreHospital;
    private ArrayList<Medico> listaMedicos;
    private ArrayList<Enfermero> listaEnfermeros;
    private ArrayList<Paciente> listaPacientes;

    /**
     * Crea un sistema vacío.
     *
     * @param nombreHospital nombre del hospital
     */
    public Sistema(String nombreHospital) {
        if (nombreHospital == null || nombreHospital.trim().length() == 0) {
            System.out.println("Error: el nombre del hospital no puede estar vacío. Se guardará \"Hospital\".");
            this.nombreHospital = "Hospital";
        } else {
            this.nombreHospital = nombreHospital.trim();
        }
        this.listaMedicos = new ArrayList<Medico>();
        this.listaEnfermeros = new ArrayList<Enfermero>();
        this.listaPacientes = new ArrayList<Paciente>();
    }

    /** @return nombre del hospital */
    public String getNombreHospital() {
        return this.nombreHospital;
    }

    /** @return cantidad de médicos registrados */
    public int getNoMedicos() {
        return this.listaMedicos.size();
    }

    /** @return cantidad de enfermeros registrados */
    public int getNoEnfermeros() {
        return this.listaEnfermeros.size();
    }

    /** @return cantidad de pacientes registrados */
    public int getNoPacientes() {
        return this.listaPacientes.size();
    }

    /**
     * Registra un médico de cualquier tipo.
     *
     * @param m médico a registrar
     */
    public void registroMedico(Medico m) {
        this.listaMedicos.add(m);
        m.registroEnSistema();
    }

    /**
     * Registra un enfermero de cualquier tipo.
     *
     * @param e enfermero a registrar
     */
    public void registroEnfermero(Enfermero e) {
        this.listaEnfermeros.add(e);
        e.registroEnSistema();
    }

    /**
     * Registra un paciente de cualquier tipo.
     *
     * @param p paciente a registrar
     */
    public void registroPaciente(Paciente p) {
        this.listaPacientes.add(p);
        p.registroEnSistema();
    }

    /**
     * Asigna un paciente a un médico (polimorfismo estático: sobrecarga).
     *
     * @param m médico que recibe al paciente
     * @param p paciente a asignar
     */
    public void asignarPaciente(Medico m, Paciente p) {
        m.agregarPaciente(p);
    }

    /**
     * Asigna un paciente a un enfermero (polimorfismo estático: sobrecarga).
     *
     * @param e enfermero que recibe al paciente
     * @param p paciente a asignar
     */
    public void asignarPaciente(Enfermero e, Paciente p) {
        e.agregarPaciente(p);
    }

    /**
     * Busca un médico por nombre.
     *
     * @param nombre nombre a buscar
     * @return el médico, o null si no existe
     */
    public Medico buscarMedicoPorNombre(String nombre) {
        for (int i = 0; i < this.listaMedicos.size(); i++) {
            if (this.listaMedicos.get(i).getNombre().equals(nombre)) {
                return this.listaMedicos.get(i);
            }
        }
        return null;
    }

    /**
     * Busca un enfermero por nombre.
     *
     * @param nombre nombre a buscar
     * @return el enfermero, o null si no existe
     */
    public Enfermero buscarEnfermeroPorNombre(String nombre) {
        for (int i = 0; i < this.listaEnfermeros.size(); i++) {
            if (this.listaEnfermeros.get(i).getNombre().equals(nombre)) {
                return this.listaEnfermeros.get(i);
            }
        }
        return null;
    }

    /**
     * Busca un paciente por nombre.
     *
     * @param nombre nombre a buscar
     * @return el paciente, o null si no existe
     */
    public Paciente buscarPacientePorNombre(String nombre) {
        for (int i = 0; i < this.listaPacientes.size(); i++) {
            if (this.listaPacientes.get(i).getNombre().equals(nombre)) {
                return this.listaPacientes.get(i);
            }
        }
        return null;
    }

    /**
     * Demuestra el polimorfismo dinámico: recorre listas de la clase base
     * (o de la interfaz) y cada objeto ejecuta SU versión del método.
     */
    public void demostrarPolimorfismo() {
        System.out.println("\n--- Pacientes (referencia de tipo Paciente) ---");
        for (int i = 0; i < this.listaPacientes.size(); i++) {
            Paciente p = this.listaPacientes.get(i);
            System.out.println("[" + p.getTipo() + "]");
            p.solicitarConsulta();
        }

        System.out.println("\n--- Médicos (referencia de tipo Medico) ---");
        for (int i = 0; i < this.listaMedicos.size(); i++) {
            Medico m = this.listaMedicos.get(i);
            System.out.println(m.getTipo() + ": " + m.getNombre() + " - pacientes: " + m.getNoPacientes() + "/"
                    + Medico.LIMITE_PACIENTES);
        }

        System.out.println("\n--- Enfermeros (referencia de tipo Enfermero) ---");
        for (int i = 0; i < this.listaEnfermeros.size(); i++) {
            Enfermero e = this.listaEnfermeros.get(i);
            System.out.println(e.getTipo() + ": " + e.getNombre() + " - pacientes: " + e.getNoPacientes() + "/"
                    + Enfermero.LIMITE_PACIENTES);
        }

        System.out.println("\n--- Todo el personal (referencia de tipo AtencionMedica) ---");
        ArrayList<AtencionMedica> personal = new ArrayList<AtencionMedica>();
        for (int i = 0; i < this.listaMedicos.size(); i++) {
            personal.add(this.listaMedicos.get(i));
        }
        for (int i = 0; i < this.listaEnfermeros.size(); i++) {
            personal.add(this.listaEnfermeros.get(i));
        }
        for (int i = 0; i < personal.size(); i++) {
            personal.get(i).verListaPacientes();
        }
    }
}
