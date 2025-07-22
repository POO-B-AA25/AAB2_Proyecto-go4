package Controller;
import java.io.Serializable;

public abstract class Pelicula implements Serializable {

    protected String titulo;
    protected String sinopsis;
    protected String genero;
    protected String clasificacion;
    protected int duracion;

    public Pelicula(String titulo, String sinopsis, String genero,
            String clasificacion, int duracion) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public abstract double getPrecioBase();

    @Override
    public String toString() {
        return String.format("%-32s | Genero: %-10s | Clas.: %-5s | Dur.: %d min",
                this.titulo, this.genero, this.clasificacion, this.duracion);
    }
}
