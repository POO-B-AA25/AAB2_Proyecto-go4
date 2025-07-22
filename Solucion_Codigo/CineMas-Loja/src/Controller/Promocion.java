package Controller;
import java.io.Serializable;

public class Promocion implements Serializable{
    private String descripcion;
    private String diaAplica;
    private double porcentajeDescuento;

    public Promocion(String descripcion, String diaAplica, double porcentajeDescuento) {
        this.descripcion = descripcion;
        this.diaAplica = diaAplica;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDiaAplica(String diaAplica) {
        this.diaAplica = diaAplica;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDiaAplica() {
        return diaAplica;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
    

    public boolean esDiaDePromocion(String diaActual) {
        return this.diaAplica.equalsIgnoreCase(diaActual);
    }
}