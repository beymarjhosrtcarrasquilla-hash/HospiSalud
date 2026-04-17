
import java.util.Scanner; // Importa la clase Scanner para leer entrada del usuario desde la consola

public class HospiSalud { // Clase principal del programa, representa el sistema HospiSalud
    public static void main(String[] args) { // Método principal que se ejecuta al iniciar el programa
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer entrada del usuario
        String[] cedulas = new String[100]; // Matriz unidimensional para almacenar las cédulas de los pacientes (cabeceras)
        String[][] infoPacientes = new String[100][5]; // Matriz bidimensional para almacenar información de pacientes: filas para pacientes, columnas para nombre, edad, fecha, cédula, género
        int numPacientes = 0; // Contador de pacientes registrados
        boolean salirPrograma = false; // Bandera para controlar si el usuario quiere salir del programa
        do { // Bucle do-while que mantiene el menú principal activo hasta que el usuario elija salir
            System.out.println("\nHospiSalud"); // Imprime el título del programa
            System.out.println("1. Registrarse"); // Opción para registrarse como nuevo paciente
            System.out.println("2. Ingresar"); // Opción para ingresar si ya está registrado
            System.out.println("3. Salir"); // Opción para salir del programa
            System.out.print("Elige una opción: "); // Pide al usuario que elija una opción
            int opcion = scanner.nextInt(); // Lee la opción elegida por el usuario
            scanner.nextLine(); // Consume el newline dejado por nextInt()
            switch (opcion) { // Estructura switch para manejar las opciones del menú
                case 1: // Si elige 1, registra un nuevo paciente
                    numPacientes = registrarse(scanner, cedulas, infoPacientes, numPacientes); // Llama al método registrarse y actualiza el contador
                    break; // Sale del switch
                case 2: // Si elige 2, intenta ingresar
                    ingresar(scanner, cedulas, infoPacientes, numPacientes); // Llama al método ingresar
                    break; // Sale del switch
                case 3: // Si elige 3, sale del programa
                    salirPrograma = true; // Cambia la bandera para salir
                    System.out.println("Saliendo..."); // Mensaje de despedida
                    break; // Sale del switch
                default: // Si la opción no es válida
                    System.out.println("Opción inválida."); // Mensaje de error
            }
        } while (!salirPrograma); // Repite el bucle mientras no se haya elegido salir
        scanner.close(); // Cierra el Scanner para liberar recursos
    }

    private static int registrarse(Scanner scanner, String[] cedulas, String[][] infoPacientes, int numPacientes) { // Método para registrar un nuevo paciente, devuelve el nuevo número de pacientes
        System.out.print("Nombre: "); // Pide el nombre del paciente
        String nombre = scanner.nextLine(); // Lee el nombre
        System.out.print("Cédula: "); // Pide la cédula
        String cedula = scanner.nextLine(); // Lee la cédula
        int index = buscarPorCedula(cedulas, numPacientes, cedula); // Busca si la cédula ya está registrada
        if (index != -1) { // Si ya existe
            System.out.println("Ya estás registrado."); // Mensaje de que ya está registrado
            return numPacientes; // Devuelve el mismo número
        } else if (numPacientes < 100) { // Si hay espacio
            cedulas[numPacientes] = cedula; // Almacena la cédula en la matriz unidimensional
            infoPacientes[numPacientes][0] = nombre; // Almacena el nombre en la primera columna
            infoPacientes[numPacientes][1] = "0"; // Inicializa edad en 0
            infoPacientes[numPacientes][2] = ""; // Inicializa fecha vacía
            infoPacientes[numPacientes][3] = cedula; // Almacena la cédula en la cuarta columna
            infoPacientes[numPacientes][4] = ""; // Inicializa género vacío
            System.out.println("Registrado exitosamente."); // Mensaje de éxito
            return numPacientes + 1; // Devuelve el número incrementado
        } else { // Si no hay espacio
            System.out.println("No hay espacio para más pacientes."); // Mensaje de error
            return numPacientes; // Devuelve el mismo número
        }
    }

    private static void ingresar(Scanner scanner, String[] cedulas, String[][] infoPacientes, int numPacientes) { // Método para que un paciente registrado ingrese al sistema
        System.out.print("Cédula: "); // Pide la cédula para verificar
        String cedula = scanner.nextLine(); // Lee la cédula
        int index = buscarPorCedula(cedulas, numPacientes, cedula); // Busca el índice del paciente por cédula
        if (index != -1) { // Si se encuentra
            System.out.println("Bienvenido " + infoPacientes[index][0]); // Da la bienvenida con el nombre
            mostrarMenu(scanner, cedulas, infoPacientes, index); // Muestra el menú del paciente
        } else { // Si no se encuentra
            System.out.println("No estás registrado. Regístrate primero."); // Mensaje de que no está registrado
        }
    }

    private static int buscarPorCedula(String[] cedulas, int numPacientes, String cedula) { // Método para buscar el índice de un paciente por su cédula
        for (int i = 0; i < numPacientes; i++) { // Bucle for que recorre las cédulas registradas
            if (cedulas[i].equals(cedula)) { // Si encuentra una coincidencia
                return i; // Devuelve el índice
            }
        }
        return -1; // Si no encuentra, devuelve -1
    }

    private static void mostrarMenu(Scanner scanner, String[] cedulas, String[][] infoPacientes, int indexPaciente) { // Método para mostrar el menú del paciente y manejar opciones
        boolean salir = false; // Bandera para salir del menú
        String[] campos = {"Nombre", "Edad", "Fecha de Nacimiento", "Cédula", "Género"}; // Array con los nombres de los campos para mostrar
        do { // Bucle do-while para el menú
            System.out.println("\nMenú de HospiSalud:"); // Título del menú
            System.out.println("1. Ver información del paciente"); // Opción para ver info
            System.out.println("2. Actualizar información del paciente"); // Opción para actualizar
            System.out.println("3. Salir"); // Opción para salir
            System.out.print("Elige una opción: "); // Pide opción
            int opcion = scanner.nextInt(); // Lee opción
            scanner.nextLine(); // Consume newline
            switch (opcion) { // Switch para opciones
                case 1: // Ver info
                    System.out.println("Información del paciente:"); // Título
                    int j = 0; // Contador para índice
                    for (String campo : campos) { // Bucle for-each para recorrer campos
                        System.out.println(campo + ": " + infoPacientes[indexPaciente][j]); // Imprime campo y valor
                        j++; // Incrementa índice
                    }
                    break; // Sale
                case 2: // Actualizar
                    actualizarInfo(scanner, infoPacientes, indexPaciente, campos); // Llama a actualizar
                    break; // Sale
                case 3: // Salir
                    salir = true; // Cambia bandera
                    System.out.println("Saliendo..."); // Mensaje
                    break; // Sale
                default: // Inválida
                    System.out.println("Opción inválida."); // Error
            }
        } while (!salir); // Repite mientras no salir
    }

    private static void actualizarInfo(Scanner scanner, String[][] infoPacientes, int indexPaciente, String[] campos) { // Método para actualizar la información del paciente
        boolean volver = false; // Bandera para volver al menú principal
        do { // Bucle do-while para el submenú
            System.out.println("\nActualizar información:"); // Título
            System.out.println("1. Nombre"); // Opciones
            System.out.println("2. Edad");
            System.out.println("3. Fecha de Nacimiento");
            System.out.println("4. Cédula");
            System.out.println("5. Género");
            System.out.println("6. Volver al menú principal");
            System.out.print("Elige una opción: "); // Pide opción
            int opcion = scanner.nextInt(); // Lee opción
            scanner.nextLine(); // Consume
            switch (opcion) { // Switch
                case 1: // Para cada caso 1-5
                case 2:
                case 3:
                case 4:
                case 5:
                    System.out.print("Nuevo " + campos[opcion - 1] + ": "); // Pide nuevo valor
                    infoPacientes[indexPaciente][opcion - 1] = scanner.nextLine(); // Actualiza en la matriz
                    break; // Sale
                case 6: // Volver
                    volver = true; // Cambia bandera
                    break; // Sale
                default: // Inválida
                    System.out.println("Opción inválida."); // Error
            }
        } while (!volver); // Repite mientras no volver
    }
}