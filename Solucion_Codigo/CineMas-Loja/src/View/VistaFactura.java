package View;

import Controller.Factura;
import Controller.FacturaPelicula;
import Controller.FacturaSnack;
import Controller.Snack;
import java.text.SimpleDateFormat;

public class VistaFactura {
    public void imprimir(Factura factura) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n\n==============================================");
        System.out.println("            FACTURA CINEMAS                       ");
        System.out.println("==============================================");
        System.out.printf("Factura Nro: %05d\n", factura.getNumero());
        System.out.println("Fecha: " + sdf.format(factura.getFecha()));
        System.out.println("----------------------------------------------");
        System.out.println("CLIENTE:");
        System.out.println("  Nombre: " + factura.getCliente().getNombre());
        System.out.println("  Cedula: " + factura.getCliente().getCedula());
        System.out.println("  Email:  " + factura.getCliente().getEmail());
        System.out.println("  Telefono: " + factura.getCliente().getTelefono());
        System.out.println("----------------------------------------------");
        System.out.println("DETALLES:");
        if (factura instanceof FacturaPelicula) {
            FacturaPelicula fp = (FacturaPelicula) factura;
            System.out.printf("  - %d Boleto(s) para: %s\n", fp.getBoletos().size(), fp.getBoletos().get(0).getFuncion().getPelicula().getTitulo());
            System.out.printf("    Sala: %s, Horario: %s\n", fp.getBoletos().get(0).getFuncion().getSala().getNombre(), fp.getBoletos().get(0).getFuncion().getHorario().getHora());
            System.out.printf("    Asientos: %s\n", fp.getDetallesAsientos());
        } else if (factura instanceof FacturaSnack) {
            FacturaSnack fs = (FacturaSnack) factura;
            for (Snack snack : fs.getSnacks()) {
                System.out.printf("  - Snack: %-26s $%6.2f\n", snack.getNombre(), snack.getPrecio());
            }
        }
        System.out.println("----------------------------------------------");
        System.out.printf("SUBTOTAL: %32s $%6.2f\n", "", factura.getSubtotal());
        if (factura.getDescuento() > 0) {
            System.out.printf("Promo Aplicada: %s\n", factura.getPromocion().getDescripcion());
            System.out.printf("DESCUENTO: %31s-$%6.2f\n", "", factura.getDescuento());
        }
        System.out.println("==============================================");
        System.out.printf("TOTAL A PAGAR: %27s $%6.2f\n", "", factura.getTotal());
        System.out.println("==============================================");
        System.out.println("\nGracias por su compra!\n");
    }
}
