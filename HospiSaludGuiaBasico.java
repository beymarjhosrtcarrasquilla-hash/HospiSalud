import java.util.Scanner;

public class HospiSaludGuiaBasico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cedulas = new String[100];
        String[][] infoPacientes = new String[100][6];
        int numPacientes = 0;
        boolean salirPrograma = false;

        while (!salirPrograma) {
            System.out.println("\nHospiSalud - Guía Básico");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                numPacientes = registrarse(scanner, cedulas, infoPacientes, numPacientes);
            } else if (opcion == 2) {
                ingresar(scanner, cedulas, infoPacientes, numPacientes);
            } else if (opcion == 3) {
                salirPrograma = true;
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }

    private static int registrarse(Scanner scanner, String[] cedulas, String[][] infoPacientes, int numPacientes) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        int index = buscarPorCedula(cedulas, numPacientes, cedula);
        if (index != -1) {
            System.out.println("Ya estás registrado.");
            return numPacientes;
        } else if (numPacientes < 100) {
            cedulas[numPacientes] = cedula;
            infoPacientes[numPacientes][0] = nombre;
            infoPacientes[numPacientes][1] = "0";
            infoPacientes[numPacientes][2] = "";
            infoPacientes[numPacientes][3] = cedula;
            infoPacientes[numPacientes][4] = "";
            infoPacientes[numPacientes][5] = "";
            System.out.println("Registrado exitosamente.");
            return numPacientes + 1;
        } else {
            System.out.println("No hay espacio para más pacientes.");
            return numPacientes;
        }
    }

    private static void ingresar(Scanner scanner, String[] cedulas, String[][] infoPacientes, int numPacientes) {
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        int index = buscarPorCedula(cedulas, numPacientes, cedula);

        if (index != -1) {
            System.out.println("Bienvenido " + infoPacientes[index][0]);
            mostrarMenu(scanner, infoPacientes, index);
        } else {
            System.out.println("No estás registrado. Regístrate primero.");
        }
    }

    private static int buscarPorCedula(String[] cedulas, int numPacientes, String cedula) {
        int i = 0;
        while (i < numPacientes) {
            if (cedulas[i].equals(cedula)) {
                return i;
            }
            i++;
        }
        return -1;
    }

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

            if (opcion == 1) {
                System.out.println("Información del paciente:");
                int j = 0;
                while (j < campos.length) {
                    System.out.println(campos[j] + ": " + infoPacientes[indexPaciente][j]);
                    j++;
                }
            } else if (opcion == 2) {
                actualizarInfo(scanner, infoPacientes, indexPaciente, campos);
            } else if (opcion == 3) {
                agendarCita(scanner, infoPacientes, indexPaciente);
            } else if (opcion == 4) {
                salir = true;
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

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

            if (opcion == 1) {
                System.out.print("Nuevo " + campos[0] + ": ");
                infoPacientes[indexPaciente][0] = scanner.nextLine();
            } else if (opcion == 2) {
                System.out.print("Nuevo " + campos[1] + ": ");
                infoPacientes[indexPaciente][1] = scanner.nextLine();
            } else if (opcion == 3) {
                System.out.print("Nuevo " + campos[2] + ": ");
                infoPacientes[indexPaciente][2] = scanner.nextLine();
            } else if (opcion == 4) {
                System.out.print("Nuevo " + campos[3] + ": ");
                infoPacientes[indexPaciente][3] = scanner.nextLine();
            } else if (opcion == 5) {
                System.out.print("Nuevo " + campos[4] + ": ");
                infoPacientes[indexPaciente][4] = scanner.nextLine();
            } else if (opcion == 6) {
                System.out.print("Nuevo " + campos[5] + ": ");
                infoPacientes[indexPaciente][5] = scanner.nextLine();
            } else if (opcion == 7) {
                volver = true;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static void agendarCita(Scanner scanner, String[][] infoPacientes, int indexPaciente) {
        System.out.print("Ingresa la fecha de la cita (ej. 2026-05-21): ");
        String fechaCita = scanner.nextLine();
        System.out.print("Ingresa el horario de la cita (ej. 14:30): ");
        String horarioCita = scanner.nextLine();
        infoPacientes[indexPaciente][5] = fechaCita + " " + horarioCita;
        System.out.println("Cita agendada para " + infoPacientes[indexPaciente][0] + ": " + infoPacientes[indexPaciente][5]);
    }
}
