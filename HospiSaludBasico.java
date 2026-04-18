import java.util.Scanner;

public class HospiSaludBasico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cedulas = new String[100];
        String[][] infoPacientes = new String[100][6];
        int numPacientes = 0;
        boolean salirPrograma = false;

        do {
            System.out.println("\nHospiSalud");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Registrarse
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Cédula: ");
                    String cedula = scanner.nextLine();

                    // Buscar si ya existe
                    int index = -1;
                    int i = 0;
                    while (i < numPacientes) {
                        if (cedulas[i].equals(cedula)) {
                            index = i;
                            i = numPacientes; // Salir del while
                        }
                        i++;
                    }

                    if (index != -1) {
                        System.out.println("Ya estás registrado.");
                    } else if (numPacientes < 100) {
                        cedulas[numPacientes] = cedula;
                        infoPacientes[numPacientes][0] = nombre;
                        infoPacientes[numPacientes][1] = "0";
                        infoPacientes[numPacientes][2] = "";
                        infoPacientes[numPacientes][3] = cedula;
                        infoPacientes[numPacientes][4] = "";
                        infoPacientes[numPacientes][5] = "";
                        System.out.println("Registrado exitosamente.");
                        numPacientes = numPacientes + 1;
                    } else {
                        System.out.println("No hay espacio para más pacientes.");
                    }
                    break;
                case 2:
                    // Ingresar
                    System.out.print("Cédula: ");
                    String cedulaIngreso = scanner.nextLine();

                    // Buscar paciente
                    int indexIngreso = -1;
                    int j = 0;
                    while (j < numPacientes) {
                        if (cedulas[j].equals(cedulaIngreso)) {
                            indexIngreso = j;
                            j = numPacientes; // Salir del while
                        }
                        j++;
                    }

                    if (indexIngreso != -1) {
                        System.out.println("Bienvenido " + infoPacientes[indexIngreso][0]);

                        // Submenú
                        boolean salirSub = false;
                        do {
                            System.out.println("\nMenú de HospiSalud:");
                            System.out.println("1. Ver información del paciente");
                            System.out.println("2. Actualizar información del paciente");
                            System.out.println("3. Agendar cita");
                            System.out.println("4. Salir");
                            System.out.print("Elige una opción: ");
                            int opcionSub = scanner.nextInt();
                            scanner.nextLine();

                            switch (opcionSub) {
                                case 1:
                                    System.out.println("Información del paciente:");
                                    String[] campos = {"Nombre", "Edad", "Fecha de Nacimiento", "Cédula", "Género", "Cita"};
                                    int k = 0;
                                    while (k < campos.length) {
                                        System.out.println(campos[k] + ": " + infoPacientes[indexIngreso][k]);
                                        k++;
                                    }
                                    break;
                                case 2:
                                    // Actualizar
                                    boolean volver = false;
                                    do {
                                        System.out.println("\nActualizar información:");
                                        System.out.println("1. Nombre");
                                        System.out.println("2. Edad");
                                        System.out.println("3. Fecha de Nacimiento");
                                        System.out.println("4. Cédula");
                                        System.out.println("5. Género");
                                        System.out.println("6. Cita");
                                        System.out.println("7. Volver al menú principal");
                                        System.out.print("Elige una opción: ");
                                        int opcionAct = scanner.nextInt();
                                        scanner.nextLine();

                                        if (opcionAct == 1) {
                                            System.out.print("Nuevo Nombre: ");
                                            infoPacientes[indexIngreso][0] = scanner.nextLine();
                                        } else if (opcionAct == 2) {
                                            System.out.print("Nuevo Edad: ");
                                            infoPacientes[indexIngreso][1] = scanner.nextLine();
                                        } else if (opcionAct == 3) {
                                            System.out.print("Nuevo Fecha de Nacimiento: ");
                                            infoPacientes[indexIngreso][2] = scanner.nextLine();
                                        } else if (opcionAct == 4) {
                                            System.out.print("Nuevo Cédula: ");
                                            infoPacientes[indexIngreso][3] = scanner.nextLine();
                                        } else if (opcionAct == 5) {
                                            System.out.print("Nuevo Género: ");
                                            infoPacientes[indexIngreso][4] = scanner.nextLine();
                                        } else if (opcionAct == 6) {
                                            System.out.print("Nuevo Cita: ");
                                            infoPacientes[indexIngreso][5] = scanner.nextLine();
                                        } else if (opcionAct == 7) {
                                            volver = true;
                                        } else {
                                            System.out.println("Opción inválida.");
                                        }
                                    } while (!volver);
                                    break;
                                case 3:
                                    // Agendar cita
                                    System.out.print("Ingresa la fecha de la cita (ej. 2026-05-21): ");
                                    String fechaCita = scanner.nextLine();
                                    System.out.print("Ingresa el horario de la cita (ej. 14:30): ");
                                    String horarioCita = scanner.nextLine();
                                    infoPacientes[indexIngreso][5] = fechaCita + " " + horarioCita;
                                    System.out.println("Cita agendada para " + infoPacientes[indexIngreso][0] + ": " + infoPacientes[indexIngreso][5]);
                                    break;
                                case 4:
                                    salirSub = true;
                                    System.out.println("Saliendo...");
                                    break;
                                default:
                                    System.out.println("Opción inválida.");
                            }
                        } while (!salirSub);
                    } else {
                        System.out.println("No estás registrado. Regístrate primero.");
                    }
                    break;
                case 3:
                    salirPrograma = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (!salirPrograma);

        scanner.close();
    }
}
