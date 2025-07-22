package Controller;

import java.io.Serializable;
import java.util.ArrayList;


public class FacturaPelicula extends Factura implements Serializable{
  private ArrayList<Boleto> boletos;
    
    public FacturaPelicula(int numero, Cliente cliente, ArrayList<Boleto> boletos, Promocion promocion) {
        super(numero, cliente, promocion);
        this.boletos = boletos;
    }

    public ArrayList<Boleto> getBoletos() { return boletos; }
    
    public String getDetallesAsientos() {
        StringBuilder detalles = new StringBuilder();
        for (int i = 0; i < boletos.size(); i++) {
            detalles.append(boletos.get(i).getAsiento().toString());
            if (i < boletos.size() - 1) detalles.append(", ");
        }
        return detalles.toString();
    }
    
    @Override
    public final void calcularTotal(String dia) {
        if (boletos == null || boletos.size() == 0) return;
        
        this.precioUnitario = boletos.get(0).getFuncion().getPelicula().getPrecioBase();
        this.subtotal = this.precioUnitario * boletos.size();
        
        if (this.promocion != null && this.promocion.esDiaDePromocion(dia)) {
            this.descuento = this.subtotal * (this.promocion.getPorcentajeDescuento() / 100.0);
        } else {
            this.descuento = 0;
        }
        this.total = this.subtotal - this.descuento;
    }
}

