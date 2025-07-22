package Controller;
import java.io.Serializable;

public class PeliculaNormal extends Pelicula implements Serializable{

    public double precio;

    public PeliculaNormal(String titulo, String sinopsis,
            String genero, String clasificacion, int duracion) {
        super(titulo, sinopsis, genero, clasificacion, duracion);
        this.precio = 7.50;
    }
    @Override
    public double getPrecioBase() {
        return this.precio;
    }
}
