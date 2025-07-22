package Controller;
import java.io.Serializable;

public class Snack implements Serializable {

    private String nombre;
    private double precio;

    public Snack(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return String.format("%-25s | Precio: $%.2f", this.nombre, this.precio);
    }
}
