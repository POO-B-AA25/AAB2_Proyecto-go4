package View;
import Controller.Funcion;
import Controller.Snack;
import java.util.ArrayList;

public class VistaMenu {
        public void mostrarFunciones(ArrayList<Funcion> funciones) {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("||                           Cartelera de Funciones : CineMas                     ||");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0; i < funciones.size(); i++) {
            System.out.printf("%-3d. %s\n", (i + 1), funciones.get(i));
        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    public void mostrarSnacks(ArrayList<Snack> snacks) {
        System.out.println("-------------------------------------------------");
        System.out.println("||              SNACKS : CineMas               ||");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < snacks.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), snacks.get(i));
        }
        System.out.println("-------------------------------------------------");
    }
}
