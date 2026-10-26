package hospital.pacientes;

/**
 * Clase base abstracta de los pacientes del hospital.
 * Contiene los datos y comportamientos que comparten todos los pacientes.
 *
 * @author Grupo 7
 * @version 2.0
 */
public abstract class Paciente {

    /** Estado: el paciente espera atención. */
    public static final int ESPERA = 0;
    /** Estado: el paciente está en consulta. */
    public static final int CONSULTA = 1;
    /** Estado: el paciente está en tratamiento. */
    public static final int TRATAMIENTO = 2;

    private String nombre;
    private String apellidos;
    private String especialidadAtencion;
    private int estado;

    /**
     * Crea un paciente en estado de espera.
     *
     * @param nombre nombre del paciente
     * @param apellidos apellidos del paciente
     * @param especialidadAtencion especialidad que requiere
     */
    public Paciente(String nombre, String apellidos, String especialidadAtencion) {
        this.nombre = textoValido(nombre, "nombre");
        this.apellidos = textoValido(apellidos, "apellidos");
        this.especialidadAtencion = textoValido(especialidadAtencion, "especialidad");
        this.estado = ESPERA;
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

    /** @return nombre del paciente */
    public String getNombre() {
        return this.nombre;
    }

    /** @return apellidos del paciente */
    public String getApellidos() {
        return this.apellidos;
    }

    /** @return apellidos y nombre juntos */
    public String getNombreCompleto() {
        return this.apellidos + " " + this.nombre;
    }

    /** @return especialidad que requiere el paciente */
    public String getEspecialidadAtencion() {
        return this.especialidadAtencion;
    }

    /** @return estado actual: 0 espera, 1 consulta, 2 tratamiento */
    public int getEstado() {
        return this.estado;
    }

    /**
     * Cambia el estado del paciente solo si el valor es válido.
     *
     * @param estado nuevo estado (0, 1 o 2)
     */
    public void setEstado(int estado) {
        if (estado < ESPERA || estado > TRATAMIENTO) {
            System.out.println("Error: estado inválido (" + estado + "). El estado no cambia.");
            return;
        }
        this.estado = estado;
    }

    /** Imprime el mensaje de registro del paciente. */
    public void registroEnSistema() {
        System.out.println("Paciente registrado: " + this.getNombreCompleto() + " (" + this.getTipo() + ")");
    }

    /** Indica si el paciente está recibiendo tratamiento. */
    public void verTratamiento() {
        if (this.estado == TRATAMIENTO) {
            System.out.println("El paciente " + this.nombre + " está recibiendo tratamiento.");
        } else {
            System.out.println("El paciente " + this.nombre + " no tiene tratamiento activo.");
        }
    }

    /** Cada tipo de paciente solicita su consulta de forma distinta. */
    public abstract void solicitarConsulta();

    /** @return nombre del tipo de paciente */
    public abstract String getTipo();
}
