package Controller;
import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {

    private int numSala;
    private String nombre;
    private int capacidad;
    private boolean disponibilidad;
    private ArrayList<Asiento> asientos;
    private int asientosPorFila;

    public Sala(int numSala, String nombre) {
        this.numSala = numSala;
        this.nombre = nombre;
        this.capacidad = 40;
        this.asientos = new ArrayList<>();

        this.asientosPorFila = 8;
        int filas = (this.capacidad + this.asientosPorFila - 1) / this.asientosPorFila;

        int asientosCreados = 0;
        for (int i = 0; i < filas; i++) {
            char filaChar = (char) ('A' + i);
            for (int j = 1; j <= asientosPorFila; j++) {
                if (asientosCreados < this.capacidad) {
                    asientos.add(new Asiento(String.valueOf(filaChar), j));
                    asientosCreados++;
                }
            }
        }
        this.disponibilidad = determinarDisponibilidad();
    }

    public boolean determinarDisponibilidad() {
        for (Asiento asiento : asientos) {
            if (!asiento.estaOcupado()) {
                return true;
            }
        }
        return false;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getNumSala() {
        return numSala;
    }

    public String getNombre() {
        return nombre;
    }
    

    public int getCapacidad() {
        return capacidad;
    }

    public boolean estaDisponible() {
        return disponibilidad;
    }

    public ArrayList<Asiento> getAsientos() {
        return asientos;
    }

    public int getAsientosPorFila() {
        return asientosPorFila;
    }

    public Asiento getAsiento(String fila, int numero) {
        for (Asiento asiento : asientos) {
            if (asiento.getFila().equalsIgnoreCase(fila) && asiento.getNumero() == numero) {
                return asiento;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Sala " + numSala + ": '" + nombre + "' (Capacidad: " + capacidad + ")";
    }
}
