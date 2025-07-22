package Controller;

import java.io.Serializable;
import java.util.ArrayList;

public class FacturaSnack extends Factura implements Serializable{

    private ArrayList<Snack> snacks;

    public FacturaSnack(int numero, Cliente cliente, ArrayList<Snack> snacks, Promocion promocion) {
        super(numero, cliente, promocion);
        this.snacks = snacks;
    }

    public void setSnacks(ArrayList<Snack> snacks) {
        this.snacks = snacks;
    }

    public ArrayList<Snack> getSnacks() {
        return snacks;
    }

    public String snackDetalles() {
        StringBuilder detalles = new StringBuilder();
        for (int i = 0; i < snacks.size(); i++) {
            detalles.append(snacks.get(i).getNombre());
            if (i < snacks.size() - 1) {
                detalles.append(", ");
            }
        }
        return detalles.toString();
    }

    @Override
    public final void calcularTotal(String dia) {
        this.subtotal = 0;
        for (Snack snack : snacks) {
            this.subtotal += snack.getPrecio();
        }

        if (snacks.size() > 0) {
            this.precioUnitario = snacks.get(0).getPrecio();
        }

        if (this.promocion != null && this.promocion.esDiaDePromocion(dia)) {
            this.descuento = this.subtotal * (this.promocion.getPorcentajeDescuento() / 100.0);
        } else {
            this.descuento = 0;
        }
        this.total = this.subtotal - this.descuento;
    }
}
