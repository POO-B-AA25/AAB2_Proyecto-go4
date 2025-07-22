
package View;

import Controller.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCine {
    private CineController controller;
    private Scanner scanner;
    private VistaMenu vistaMenu;
    private VistaAsientos vistaAsientos;
    private VistaFactura vistaFactura;

    public SistemaCine() {
        this.controller = new CineController();
        this.scanner = new Scanner(System.in);
        this.vistaMenu = new VistaMenu();
        this.vistaAsientos = new VistaAsientos();
        this.vistaFactura = new VistaFactura();
    }

    public void iniciarApp() {
        int opcion;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("|       MENU PRINCIPAL - CineMas        |");
            System.out.println("-----------------------------------------");
            System.out.println("(1) Comprar Boleto(s) de Pelicula");
            System.out.println("(2) Comprar Snacks");
            System.out.println("(3) Ver Cartelera de Peliculas");
            System.out.println("(4) Ver Registro de Facturas");
            System.out.println("(5) Salir");
            System.out.print(">> Seleccione una opcion: ");
            
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    operacionComprarBoleto();
                    break;
                case 2:
                    operacionComprarSnacks();
                    break;
                case 3:
                    operacionVerCartelera();
                    break;
                case 4:
                    operacionVerRegistroFacturas();
                    break;
                case 5:
                    System.out.println("Gracias por usar el sistema");
                    break;
                default:
                    System.out.println("\n[ERROR] Opcion no valida, intente de nuevo.");
            }
        } while (opcion != 5);
        scanner.close();
    }
    
    private void operacionVerCartelera() {
        vistaMenu.mostrarFunciones(controller.getFunciones());
        System.out.print("\nPresione Enter para volver al menu principal |> ");
        scanner.nextLine();
    }
    
    private void operacionVerRegistroFacturas() {
        ArrayList<String> registro = controller.getRegistroFacturas();
        System.out.println("\n|  REGISTRO DE FACTURAS  |");
        if (registro.size() == 0) {
            System.out.println("Aún no se han generado facturas.");
        } else {
            for (String linea : registro) {
                System.out.println(linea);
            }
        }
        System.out.print("\nPresione Enter para volver al menu principal |> ");
        scanner.nextLine();
    }

    private void operacionComprarBoleto() {
        vistaMenu.mostrarFunciones(controller.getFunciones());
        System.out.print("Seleccione el numero de la Pelicula: ");
        int opcionFuncion = Integer.parseInt(scanner.nextLine()) - 1;
        Funcion funcion = controller.getFunciones().get(opcionFuncion);

        System.out.print("Cuantos boletos desea comprar?: ");
        int cantidadBoletos = Integer.parseInt(scanner.nextLine());
        
        ArrayList<Asiento> asientosSeleccionados = new ArrayList<>();
        for (int i = 0; i < cantidadBoletos; i++) {
            vistaAsientos.mostrarMapa(funcion.getSala());
            System.out.printf("Seleccione asiento para el boleto %d de %d (ej. A5): ", (i + 1), cantidadBoletos);
            String seleccionAsientoStr = scanner.nextLine().toUpperCase().trim();
            String fila = seleccionAsientoStr.substring(0, 1);
            int numero = Integer.parseInt(seleccionAsientoStr.substring(1));
            Asiento asiento = funcion.getSala().getAsiento(fila, numero);

            if (asiento == null || asiento.estaOcupado()) {
                System.out.println("\n[ ERROR ] Asiento no valido u ocupado.");
                return;
            }
            asiento.ocupar();
            asientosSeleccionados.add(asiento);
        }
        
        System.out.print("Ingrese su nombre: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese su email: ");
        String emailCliente = scanner.nextLine();
        System.out.print("Ingrese su telefono: ");
        String telefonoCliente = scanner.nextLine();
        Cliente cliente = new Cliente("9999999999", nombreCliente, emailCliente, telefonoCliente);

        FacturaPelicula factura = controller.generarFacturaPelicula(cliente, funcion, asientosSeleccionados, "Martes");
        
        vistaFactura.imprimir(factura);
    }

    private void operacionComprarSnacks() {
        vistaMenu.mostrarSnacks(controller.getSnacks());
        ArrayList<Snack> snacksComprados = new ArrayList<>();
        String opcionSnack;
        
        do {
            System.out.print("Seleccione un snack por numero (o escriba 'fin' para terminar): ");
            opcionSnack = scanner.nextLine();
            if (!opcionSnack.equalsIgnoreCase("fin")) {
                try {
                    int numSnack = Integer.parseInt(opcionSnack) - 1;
                    Snack snackSeleccionado = controller.getSnacks().get(numSnack);
                    snacksComprados.add(snackSeleccionado);
                    System.out.println("'" + snackSeleccionado.getNombre() + "' agregado al carrito.");
                } catch (Exception e) {
                    System.out.println("[ ERROR ] Seleccion no valida.");
                }
            }
        } while (!opcionSnack.equalsIgnoreCase("fin"));

        if (snacksComprados.size() == 0) {
            System.out.println("No se selecciono ningun snack.");
            return;
        }
        System.out.print("Ingrese su nombre: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese su email: ");
        String emailCliente = scanner.nextLine();
        System.out.print("Ingrese su telefono: ");
        String telefonoCliente = scanner.nextLine();
        Cliente cliente = new Cliente("9999999999", nombreCliente, emailCliente, telefonoCliente);
        FacturaSnack factura = controller.generarFacturaSnack(cliente, snacksComprados, "Martes");
        vistaFactura.imprimir(factura);
    }
}