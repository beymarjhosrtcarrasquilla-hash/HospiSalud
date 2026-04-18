import java.util.Scanner;

public class HospiSaludGuia {
    public static void main(String[] args) {
        // 1. Estructura exterior: variables principales y bucle del menú
        Scanner scanner = new Scanner(System.in);
        String[] cedulas = new String[100];
        String[][] infoPacientes = new String[100][6];
        int numPacientes = 0;
        boolean salirPrograma = false;

        while (!salirPrograma) {
            System.out.println("\nHospiSalud - versión guía");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    numPacientes = registrarse(scanner, cedulas, infoPacientes, numPacientes);
                    break;
                case 2:
                    ingresar(scanner, cedulas, infoPacientes, numPacientes);
                    break;
                case 3:
                    salirPrograma = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }

    // 2. Método para registrar pacientes
    private static int registrarse(Scanner scanner, String[] cedulas, String[][] infoPacientes, int numPacientes) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        int index = buscarPorCedula(cedulas, numPacientes, cedula);
        if (index != -1) {
            System.out.println("Ya estás registrado.");
            return numPacientes;
        }

        if (numPacientes >= 100) {
            System.out.println("No hay espacio para más pacientes.");
            return numPacientes;
        }

        cedulas[numPacientes] = cedula;
        infoPacientes[numPacientes][0] = nombre;
        infoPacientes[numPacientes][1] = "0";
        infoPacientes[numPacientes][2] = "";
        infoPacientes[numPacientes][3] = cedula;
        infoPacientes[numPacientes][4] = "";
        infoPacientes[numPacientes][5] = "";

        System.out.println("Registrado exitosamente.");
        return numPacientes + 1;
    }

    // 3. Método para ingresar al sistema
    private static void ingresar(Scanner scanner, String[] cedulas, String[][] infoPacientes, int numPacientes) {
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        int index = buscarPorCedula(cedulas, numPacientes, cedula);

        if (index == -1) {
            System.out.println("No estás registrado. Regístrate primero.");
            return;
        }

        System.out.println("Bienvenido " + infoPacientes[index][0]);
        mostrarMenu(scanner, infoPacientes, index);
    }

    // 4. Método para buscar pacientes por cédula
    private static int buscarPorCedula(String[] cedulas, int numPacientes, String cedula) {
        for (int i = 0; i < numPacientes; i++) {
            if (cedulas[i].equals(cedula)) {
                return i;
            }
        }
        return -1;
    }

    // 5. Menú interno del paciente
    private static void mostrarMenu(Scanner scanner, String[][] infoPacientes, int indexPaciente) {
        String[] campos = {"Nombre", "Edad", "Fecha de Nacimiento", "Cédula", "Género", "Cita"};
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú de HospiSalud:");
            System.out.println("1. Ver información del paciente");
            System.out.println("2. Actualizar información del paciente");
            System.out.println("3. Agendar cita");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Información del paciente:");
                    for (int i = 0; i < campos.length; i++) {
                        System.out.println(campos[i] + ": " + infoPacientes[indexPaciente][i]);
                    }
                    break;
                case 2:
                    actualizarInfo(scanner, infoPacientes, indexPaciente, campos);
                    break;
                case 3:
                    agendarCita(scanner, infoPacientes, indexPaciente);
                    break;
                case 4:
                    salir = true;
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // 6. Método para actualizar la información del paciente
    private static void actualizarInfo(Scanner scanner, String[][] infoPacientes, int indexPaciente, String[] campos) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\nActualizar información:");
            System.out.println("1. Nombre");
            System.out.println("2. Edad");
            System.out.println("3. Fecha de Nacimiento");
            System.out.println("4. Cédula");
            System.out.println("5. Género");
            System.out.println("6. Cita");
            System.out.println("7. Volver al menú principal");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Nuevo " + campos[opcion - 1] + ": ");
                infoPacientes[indexPaciente][opcion - 1] = scanner.nextLine();
            } else if (opcion == 7) {
                volver = true;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    // 7. Método para agendar cita
    private static void agendarCita(Scanner scanner, String[][] infoPacientes, int indexPaciente) {
        System.out.print("Ingresa la fecha de la cita (ej. 2026-05-21): ");
        String fechaCita = scanner.nextLine();
        System.out.print("Ingresa el horario de la cita (ej. 14:30): ");
        String horarioCita = scanner.nextLine();
        infoPacientes[indexPaciente][5] = fechaCita + " " + horarioCita;
        System.out.println("Cita agendada para " + infoPacientes[indexPaciente][0] + ": " + infoPacientes[indexPaciente][5]);
    }
}
