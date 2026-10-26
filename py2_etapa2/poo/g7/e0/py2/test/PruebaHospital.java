import hospital.interfaces.AtencionMedica;
import hospital.pacientes.PacienteEspecialidad;
import hospital.pacientes.PacienteGeneral;
import hospital.personal.*;
import hospital.sistema.Sistema;
import java.util.Scanner;

/**
 * Programa principal del simulador hospitalario (Etapa 2).
 * Está fuera de los paquetes, en la carpeta test, y usa import y nombres calificados.
 *
 * @author Grupo 7
 * @version 2.0
 */
public class PruebaHospital {

    /** Lee un texto de una línea. */
    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    /** Lee un entero sin usar excepciones: repite hasta que el usuario escriba solo dígitos. */
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            boolean esNumero = linea.length() > 0 && linea.length() <= 5;
            for (int i = 0; i < linea.length(); i++) {
                if (!Character.isDigit(linea.charAt(i))) {
                    esNumero = false;
                }
            }
            if (esNumero) {
                return Integer.parseInt(linea);
            }
            System.out.println("Error: escribe solo un número entero.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema("Hospital General POO");
        int opcion;

        do {
            System.out.println("\n===== MENU " + sistema.getNombreHospital() + " =====");
            System.out.println("1. Registrar medico (general o especialista)");
            System.out.println("2. Registrar enfermero (general, especialista o jefe)");
            System.out.println("3. Registrar paciente (general o de especialidad)");
            System.out.println("4. Asignar paciente a medico");
            System.out.println("5. Asignar paciente a enfermero");
            System.out.println("6. Ver lista de pacientes de un medico");
            System.out.println("7. Ver lista de pacientes de un enfermero");
            System.out.println("8. Paciente: Ver estado de tratamiento");
            System.out.println("9. Paciente: Solicitar consulta");
            System.out.println("10. Medico: Solicitar paciente y dar consulta");
            System.out.println("11. Medico/Enfermero: Dar tratamiento");
            System.out.println("12. Jefe de enfermeria: Agregar enfermero a su equipo");
            System.out.println("13. Jefe de enfermeria: Supervisar equipo");
            System.out.println("14. Demostracion de polimorfismo");
            System.out.println("0. Salir");
            opcion = leerEntero(sc, "Elige una opcion: ");

            switch (opcion) {
                case 1: {
                    int tipo = leerEntero(sc, "Tipo de medico (1=General, 2=Especialista): ");
                    if (tipo != 1 && tipo != 2) {
                        System.out.println("Tipo de medico no valido.");
                        break;
                    }
                    String nombre = leerTexto(sc, "Nombre del medico: ");
                    String cedula = leerTexto(sc, "Cedula: ");
                    if (tipo == 1) {
                        String consultorio = leerTexto(sc, "Consultorio: ");
                        sistema.registroMedico(new MedicoGeneral(nombre, cedula, consultorio));
                    } else {
                        String especialidad = leerTexto(sc, "Especialidad: ");
                        String certificacion = leerTexto(sc, "Certificacion: ");
                        sistema.registroMedico(new MedicoEspecialista(nombre, cedula, especialidad, certificacion));
                    }
                    break;
                }

                case 2: {
                    int tipo = leerEntero(sc, "Tipo de enfermero (1=General, 2=Especialista, 3=Jefe): ");
                    if (tipo < 1 || tipo > 3) {
                        System.out.println("Tipo de enfermero no valido.");
                        break;
                    }
                    String nombre = leerTexto(sc, "Nombre del enfermero: ");
                    String cedula = leerTexto(sc, "Cedula: ");
                    if (tipo == 1) {
                        String turno = leerTexto(sc, "Turno: ");
                        sistema.registroEnfermero(new EnfermeroGeneral(nombre, cedula, turno));
                    } else if (tipo == 2) {
                        String especialidad = leerTexto(sc, "Especialidad: ");
                        String certificacion = leerTexto(sc, "Certificacion: ");
                        sistema.registroEnfermero(new EnfermeroEspecialista(nombre, cedula, especialidad, certificacion));
                    } else {
                        String area = leerTexto(sc, "Area que coordina: ");
                        sistema.registroEnfermero(new JefeEnfermeria(nombre, cedula, area));
                    }
                    break;
                }

                case 3: {
                    int tipo = leerEntero(sc, "Tipo de paciente (1=General, 2=Especialidad): ");
                    if (tipo != 1 && tipo != 2) {
                        System.out.println("Tipo de paciente no valido.");
                        break;
                    }
                    String nombre = leerTexto(sc, "Nombre del paciente: ");
                    String apellidos = leerTexto(sc, "Apellidos del paciente: ");
                    if (tipo == 1) {
                        String motivo = leerTexto(sc, "Motivo de consulta: ");
                        sistema.registroPaciente(new PacienteGeneral(nombre, apellidos, motivo));
                    } else {
                        String especialidad = leerTexto(sc, "Especialidad que necesita: ");
                        String diagnostico = leerTexto(sc, "Diagnostico previo: ");
                        sistema.registroPaciente(new PacienteEspecialidad(nombre, apellidos, especialidad, diagnostico));
                    }
                    break;
                }

                case 4: {
                    Medico m = sistema.buscarMedicoPorNombre(leerTexto(sc, "Nombre del medico: "));
                    // Aqui se usa el nombre calificado (Fully Qualified Name) en lugar de import
                    hospital.pacientes.Paciente p = sistema.buscarPacientePorNombre(leerTexto(sc, "Nombre del paciente a asignar: "));
                    if (m != null && p != null) {
                        sistema.asignarPaciente(m, p);
                    } else {
                        System.out.println("No se encontro al medico o al paciente.");
                    }
                    break;
                }

                case 5: {
                    Enfermero e = sistema.buscarEnfermeroPorNombre(leerTexto(sc, "Nombre del enfermero: "));
                    hospital.pacientes.Paciente p = sistema.buscarPacientePorNombre(leerTexto(sc, "Nombre del paciente a asignar: "));
                    if (e != null && p != null) {
                        sistema.asignarPaciente(e, p);
                    } else {
                        System.out.println("No se encontro al enfermero o al paciente.");
                    }
                    break;
                }

                case 6: {
                    Medico m = sistema.buscarMedicoPorNombre(leerTexto(sc, "Nombre del medico: "));
                    if (m != null) {
                        m.verListaPacientes();
                    } else {
                        System.out.println("No se encontro ese medico.");
                    }
                    break;
                }

                case 7: {
                    Enfermero e = sistema.buscarEnfermeroPorNombre(leerTexto(sc, "Nombre del enfermero: "));
                    if (e != null) {
                        e.verListaPacientes();
                    } else {
                        System.out.println("No se encontro ese enfermero.");
                    }
                    break;
                }

                case 8: {
                    hospital.pacientes.Paciente p = sistema.buscarPacientePorNombre(leerTexto(sc, "Nombre del paciente: "));
                    if (p != null) {
                        p.verTratamiento();
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                }

                case 9: {
                    hospital.pacientes.Paciente p = sistema.buscarPacientePorNombre(leerTexto(sc, "Nombre del paciente: "));
                    if (p != null) {
                        p.solicitarConsulta(); // polimorfismo dinamico: cada tipo de paciente responde distinto
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                }

                case 10: {
                    Medico m = sistema.buscarMedicoPorNombre(leerTexto(sc, "Nombre del medico: "));
                    hospital.pacientes.Paciente p = sistema.buscarPacientePorNombre(leerTexto(sc, "Nombre del paciente: "));
                    if (m != null && p != null) {
                        if (m.solicitarPaciente(sistema, p)) {
                            m.darConsulta(p); // polimorfismo dinamico: MedicoGeneral o MedicoEspecialista
                        }
                    } else {
                        System.out.println("Datos incorrectos.");
                    }
                    break;
                }

                case 11: {
                    int tipo = leerEntero(sc, "Quien dara el tratamiento? (1=Medico, 2=Enfermero): ");
                    String nombreProf = leerTexto(sc, "Nombre del profesional: ");
                    hospital.pacientes.Paciente p = sistema.buscarPacientePorNombre(leerTexto(sc, "Nombre del paciente: "));
                    AtencionMedica profesional = null; // referencia de la interfaz
                    if (tipo == 1) {
                        profesional = sistema.buscarMedicoPorNombre(nombreProf);
                    } else if (tipo == 2) {
                        profesional = sistema.buscarEnfermeroPorNombre(nombreProf);
                    } else {
                        System.out.println("Opcion de profesional no valida.");
                        break;
                    }
                    if (profesional != null && p != null) {
                        profesional.darTratamiento(p);
                    } else {
                        System.out.println("Profesional o paciente no encontrado.");
                    }
                    break;
                }

                case 12: {
                    Enfermero jefe = sistema.buscarEnfermeroPorNombre(leerTexto(sc, "Nombre del jefe de enfermeria: "));
                    Enfermero e = sistema.buscarEnfermeroPorNombre(leerTexto(sc, "Nombre del enfermero a agregar: "));
                    if (jefe instanceof JefeEnfermeria && e != null) {
                        ((JefeEnfermeria) jefe).agregarAEquipo(e);
                    } else {
                        System.out.println("Datos incorrectos (el primero debe ser un jefe de enfermeria registrado).");
                    }
                    break;
                }

                case 13: {
                    Enfermero jefe = sistema.buscarEnfermeroPorNombre(leerTexto(sc, "Nombre del jefe de enfermeria: "));
                    if (jefe instanceof JefeEnfermeria) {
                        ((JefeEnfermeria) jefe).supervisarEquipo();
                    } else {
                        System.out.println("Ese nombre no corresponde a un jefe de enfermeria registrado.");
                    }
                    break;
                }

                case 14:
                    sistema.demostrarPolimorfismo();
                    break;

                case 0:
                    System.out.println("Saliendo del simulador...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
