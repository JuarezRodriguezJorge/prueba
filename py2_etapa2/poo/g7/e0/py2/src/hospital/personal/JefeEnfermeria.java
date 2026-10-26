package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;
import java.util.ArrayList;

/**
 * Jefe de enfermería. Cuida pacientes generales (máximo 3, como todo enfermero)
 * y además supervisa a un equipo de enfermeros.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class JefeEnfermeria extends Enfermero {

    private String areaCoordinacion;
    private ArrayList<Enfermero> equipo;

    /**
     * Crea un jefe de enfermería. Su especialidad siempre es "General".
     *
     * @param nombre nombre del jefe
     * @param cedula cédula profesional
     * @param areaCoordinacion área que coordina
     */
    public JefeEnfermeria(String nombre, String cedula, String areaCoordinacion) {
        super(nombre, cedula, "General");
        this.areaCoordinacion = textoValido(areaCoordinacion, "área de coordinación");
        this.equipo = new ArrayList<Enfermero>();
    }

    /** @return área que coordina */
    public String getAreaCoordinacion() {
        return this.areaCoordinacion;
    }

    /** @return cantidad de enfermeros en su equipo */
    public int getNoEquipo() {
        return this.equipo.size();
    }

    /**
     * Agrega un enfermero al equipo supervisado.
     *
     * @param e enfermero a supervisar
     * @return true si se agregó
     */
    public boolean agregarAEquipo(Enfermero e) {
        if (e == null) {
            System.out.println("Error: el enfermero no existe.");
            return false;
        }
        if (e == this) {
            System.out.println("Error: el jefe no puede supervisarse a sí mismo.");
            return false;
        }
        if (this.equipo.indexOf(e) != -1) {
            System.out.println("Error: " + e.getNombre() + " ya está en el equipo de " + this.getNombre() + ".");
            return false;
        }
        this.equipo.add(e);
        System.out.println(e.getNombre() + " fue agregado al equipo de " + this.getNombre() + ".");
        return true;
    }

    /** Comportamiento propio: supervisa la carga de pacientes de cada enfermero del equipo. */
    public void supervisarEquipo() {
        System.out.println("\nSupervisión del equipo de " + this.getNombre() + " (área: "
                + this.areaCoordinacion + "):");
        if (this.equipo.size() == 0) {
            System.out.println("  (el equipo está vacío)");
            return;
        }
        for (int i = 0; i < this.equipo.size(); i++) {
            Enfermero e = this.equipo.get(i);
            String aviso = "";
            if (e.getNoPacientes() >= Enfermero.LIMITE_PACIENTES) {
                aviso = "  <- CARGA COMPLETA";
            }
            System.out.println("  " + (i + 1) + ". " + e.getNombre() + " (" + e.getTipo() + ") - pacientes: "
                    + e.getNoPacientes() + "/" + Enfermero.LIMITE_PACIENTES + aviso);
        }
    }

    /** Solo atiende pacientes generales. */
    public boolean puedeAtender(Paciente p) {
        return p instanceof PacienteGeneral;
    }

    /** Sobrescribe darTratamiento: conserva las reglas de Enfermero y agrega su propio mensaje. */
    public boolean darTratamiento(Paciente p) {
        boolean ok = super.darTratamiento(p);
        if (ok) {
            System.out.println("  El jefe de enfermería registra el tratamiento en el área " + this.areaCoordinacion + ".");
        }
        return ok;
    }

    /** @return tipo de enfermero */
    public String getTipo() {
        return "Jefe de enfermería";
    }
}
