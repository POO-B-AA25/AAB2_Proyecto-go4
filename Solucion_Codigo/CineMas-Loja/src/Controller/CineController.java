package Controller;
import Model.Catalogo;
import Model.FacturaDB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class CineController implements Serializable {
    private ArrayList<Funcion> funciones;
    private ArrayList<Snack> snacks;
    private Promocion promocionActual;
    private int proximoNumeroFactura;
    private Catalogo catalogo;
    private FacturaDB facturaDB;

    public CineController() {
        this.catalogo = new Catalogo();
        this.facturaDB = new FacturaDB();
        
        this.proximoNumeroFactura = facturaDB.consultarUltimoNumeroFactura() + 1;
        
        ArrayList<Pelicula> peliculas = catalogo.cargarPeliculasCSV("carteleraPeliculas.csv");
        this.snacks = catalogo.cargarSnacksCSV("menuSnacks.csv");
        
        ArrayList<Sala> salas = new ArrayList<>();
        salas.add(new Sala(1, "Sala Premium"));
        salas.add(new Sala(2, "Sala 3D"));
        salas.add(new Sala(3, "Sala Infantil"));
        
        this.funciones = new ArrayList<>();
        for (Pelicula peli : peliculas) {
            Sala salaAsignada = getSalaParaPelicula(peli, salas);
            Horario horario1 = new Horario(new Date(), "19:30", peli, salaAsignada);
            funciones.add(new Funcion(peli, salaAsignada, horario1));
            if (peli instanceof PeliculaNormal) {
                Horario horario2 = new Horario(new Date(), "22:00", peli, salaAsignada);
                funciones.add(new Funcion(peli, salaAsignada, horario2));
            }
        }
        
        ArrayList<String[]> asientosOcupados = facturaDB.cargarAsientosOcupados();
        for (String[] infoAsiento : asientosOcupados) {
            String nombreSala = infoAsiento[0];
            String asientoId = infoAsiento[1];
            for (Sala sala : salas) {
                if (sala.getNombre().equals(nombreSala)) {
                    String fila = asientoId.substring(0, 1);
                    int numero = Integer.parseInt(asientoId.substring(1));
                    Asiento asiento = sala.getAsiento(fila, numero);
                    if (asiento != null) {
                        asiento.ocupar();
                    }
                    break; 
                }
            }
        }
        
        this.promocionActual = new Promocion("Martes Loco", "Martes", 50.0);
    }
    
    private Sala getSalaParaPelicula(Pelicula pelicula, ArrayList<Sala> salas) {
        if (pelicula instanceof Pelicula3D) {
            return getSalaPorNombre("Sala 3D", salas);
        } else if (pelicula instanceof PeliculaInfantil) {
            return getSalaPorNombre("Sala Infantil", salas);
        } else {
            return getSalaPorNombre("Sala Premium", salas);
        }
    }

    private Sala getSalaPorNombre(String nombre, ArrayList<Sala> salas) {
        for (Sala sala : salas) {
            if (sala.getNombre().equals(nombre)) {
                return sala;
            }
        }
        return null;
    }
    
    public FacturaPelicula generarFacturaPelicula(Cliente cliente, Funcion funcion, ArrayList<Asiento> asientos, String dia) {
        ArrayList<Boleto> boletos = new ArrayList<>();
        for (Asiento asiento : asientos) {
            asiento.ocupar();
            boletos.add(new Boleto(funcion, asiento));
        }
        
        FacturaPelicula factura = new FacturaPelicula(proximoNumeroFactura, cliente, boletos, this.promocionActual);
        factura.calcularTotal(dia);
        facturaDB.guardarFacturaPelicula(factura);
        
        proximoNumeroFactura++;
        return factura;
    }
    
    public FacturaSnack generarFacturaSnack(Cliente cliente, ArrayList<Snack> snacksComprados, String dia) {
        FacturaSnack factura = new FacturaSnack(proximoNumeroFactura, cliente, snacksComprados, this.promocionActual);
        factura.calcularTotal(dia);
        facturaDB.guardarFacturaSnack(factura);
        
        proximoNumeroFactura++;
        return factura;
    }
    
    public ArrayList<String> getRegistroFacturas() {
        return facturaDB.consultarRegistroFacturas();
    }

    public ArrayList<Funcion> getFunciones() { return funciones; }
    public ArrayList<Snack> getSnacks() { return snacks; }
}
