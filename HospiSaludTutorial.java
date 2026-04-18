import java.util.Scanner;

public class HospiSaludTutorial {
    public static void main(String[] args) {
        // PASO 1: Aquí empieza el programa y se muestra el menú principal.
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia la entrada después de leer el número

            if (opcion == 1) {
                System.out.println("Has elegido registrarte.");
                // Aquí se llamará al método registrarse()
            } else if (opcion == 2) {
                System.out.println("Has elegido ingresar.");
                // Aquí se llamará al método ingresar()
            } else if (opcion == 3) {
                salir = true;
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción inválida. Elige 1, 2 o 3.");
            }
        }

        scanner.close();
    }
}
