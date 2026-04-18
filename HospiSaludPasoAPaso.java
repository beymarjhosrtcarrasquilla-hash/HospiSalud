import java.util.Scanner;

public class HospiSaludPasoAPaso {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salirPrograma = false;

        while (!salirPrograma) {
            System.out.println("\nHospiSalud - Paso a Paso");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Regístrate aquí...");
                    // Aquí irá el método registrarse()
                    break;
                case 2:
                    System.out.println("Ingresa con tu cédula...");
                    // Aquí irá el método ingresar()
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
}
