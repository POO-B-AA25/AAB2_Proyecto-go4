package Controller;
import java.util.ArrayList;
import java.util.Date;

public class Horario {
    protected Date fecha;
    protected String hora;
    protected ArrayList<Pelicula> peliculas;
    protected ArrayList<Sala> salas;

    public Horario(Date fecha, String hora, Pelicula pelicula, Sala sala) {
        this.fecha = fecha;
        this.hora = hora;
        this.peliculas = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.peliculas.add(pelicula);
        this.salas.add(sala);
    }

    public Date getFecha() {
        return fecha; 
    }
    public String getHora() { 
        return hora; 
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }
    

    @Override
    public String toString() {
        return hora;
    }
}
