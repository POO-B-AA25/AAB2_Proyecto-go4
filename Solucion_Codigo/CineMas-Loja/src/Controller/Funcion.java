package Controller;

public class Funcion {

    protected Pelicula pelicula;
    protected Sala sala;
    protected Horario horario;

    public Funcion(Pelicula pelicula, Sala sala, Horario horario) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public Horario getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return String.format("%-30s | Sala: %-15s | Horario: %s",
                pelicula.getTitulo(), sala.getNombre(), horario.getHora());
    }
}
