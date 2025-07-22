package Controller;

import java.io.Serializable;

public class Pelicula3D extends Pelicula implements Serializable {

    public double precio;

    public Pelicula3D(String titulo, String sinopsis,
            String genero, String clasificacion, int duracion) {
        super(titulo, sinopsis, genero, clasificacion, duracion);
        this.precio = 10.00;
    }

    @Override
    public double getPrecioBase() {
        return this.precio;
    }
}
