package Controller;

import java.io.Serializable;

public class PeliculaInfantil extends Pelicula implements Serializable {

    public double precio;

    public PeliculaInfantil(String titulo, String sinopsis,
            String genero, String clasificacion, int duracion) {
        super(titulo, sinopsis, genero, clasificacion, duracion);
        this.precio = 5.00;
    }

    @Override
    public double getPrecioBase() {
        return this.precio;
    }
}
