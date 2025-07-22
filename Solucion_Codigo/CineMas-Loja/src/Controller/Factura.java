package Controller;
import java.io.Serializable;
import java.util.Date;

public abstract class Factura implements Serializable {

    protected int numero;
    protected Date fecha = new Date();
    protected Cliente cliente;
    protected Promocion promocion;
    protected double subtotal;
    protected double descuento;
    protected double total;
    protected double precioUnitario;

    public Factura(int numero, Cliente cliente, Promocion promocion) {
        this.numero = numero;
        this.cliente = cliente;
        this.promocion = promocion;
    }

    public int getNumero() {
        return numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
    

    public abstract void calcularTotal(String dia);
}
