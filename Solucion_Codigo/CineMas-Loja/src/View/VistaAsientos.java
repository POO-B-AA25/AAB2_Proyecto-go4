
package View;

import Controller.Asiento;
import Controller.Sala;

public class VistaAsientos {
    public void mostrarMapa(Sala sala) {
        System.out.println("----------------------------------");
        System.out.println("|      SELECCION DE ASIENTOS     |");
        System.out.println("----------------------------------");
        System.out.println("          [ PANTALLA ]          ");
        int asientosPorFila = sala.getAsientosPorFila();
        for (int i = 0; i < sala.getAsientos().size(); i++) {
            Asiento asiento = sala.getAsientos().get(i);
            if (i % asientosPorFila == 0) {
                System.out.println();
                System.out.print(asiento.getFila() + "  ");
            }
            System.out.print(asiento.estaOcupado() ? "[X]" : "[-]");
        }
        System.out.println("\n----------------------------------");
    }
}

