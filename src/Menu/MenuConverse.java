package Menu;

import java.util.Scanner;

public class MenuConversor {
    private Scanner scanner;

    public MenuConversor() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("****** Conversor de Monedas ******");
            System.out.println("0. Salir");
            System.out.println("1. Convertir moneda");

            System.out.print("Ingrese el número de la opción deseada: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del conversor de monedas...");
                    break;
                case 1:
                    convertirMoneda();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    private void convertirMoneda() {
        System.out.println("Ingrese la cantidad a cambiar:");
        double cantidad = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese la moneda original (ejemplo: USD, EUR, etc.):");
        String monedaOriginal = scanner.nextLine();
        System.out.println("Ingrese la moneda a la que desea convertir:");
        String monedaDestino = scanner.nextLine();

        // Aquí puedes llamar a tus métodos para realizar la conversión
        // Por ahora, solo imprimimos los datos ingresados
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Moneda original: " + monedaOriginal);
        System.out.println("Moneda destino: " + monedaDestino);
    }
}
