package Menu;

import Api.ExchangeRateAPI;

import java.util.Scanner;

public class MenuConverse {
    private Scanner scanner;

    public MenuConverse() {
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int opcion;
        do {
            System.out.println();
            System.out.println();
            System.out.println("************************ CONVERSOR DE UNIDADES ************************");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Listar códigos aceptados");
            System.out.println();
            System.out.println("***********************************************************************");
            System.out.print("Ingrese el número de la opción deseada: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del conversor de monedas...");
                    break;
                case 1:
                    convertCurrency();
                    break;
                case 2:
                    System.out.println("Estos son los codigos aceptados para el cambio ");
                    showCodes();
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    private void convertCurrency() {
        ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI();

        System.out.print("Ingrese la cantidad a cambiar:");
        int amount = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Ingrese la moneda original (ejemplo: USD, EUR, COP, Puedes ver el listado de códigos en la opcion 2 del menú.):");

        String baseCurrency = scanner.nextLine();

        System.out.println("Ingrese la moneda a la que desea convertir:");
        String targetCurrency = scanner.nextLine();

        exchangeRateAPI.convertCurrency(baseCurrency, targetCurrency, amount);

    }

    private void showCodes(){
        ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI();
        exchangeRateAPI.showList();
    }



}
