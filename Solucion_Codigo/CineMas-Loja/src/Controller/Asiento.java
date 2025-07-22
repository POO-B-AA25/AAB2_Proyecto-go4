package Controller;
import java.io.Serializable;

public class Asiento implements Serializable {

    private String fila;
    private int numero;
    private boolean ocupado = false;

    public Asiento(String fila, int numero) {
        this.fila = fila;
        this.numero = numero;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }
    public String getFila() {
        return fila;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getNumero() {
        return numero;
    }
    
    public boolean estaOcupado() {
        return ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    @Override
    public String toString() {
        return fila + numero;
    }
}
