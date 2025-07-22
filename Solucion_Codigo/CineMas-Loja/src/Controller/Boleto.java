package Controller;
import java.io.Serializable;

public class Boleto implements Serializable{
    protected Funcion funcion;
    protected Asiento asiento;

    public Boleto(Funcion funcion, Asiento asiento) {
        this.funcion = funcion;
        this.asiento = asiento;
    }
    public Funcion getFuncion() { 
        return funcion; }
    public Asiento getAsiento() { 
        return asiento; }
}