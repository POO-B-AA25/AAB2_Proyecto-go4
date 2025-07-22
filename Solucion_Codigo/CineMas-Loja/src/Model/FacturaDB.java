
package Model;

import Controller.FacturaPelicula;
import Controller.FacturaSnack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FacturaDB {

    public FacturaDB() {
    }
    
    public int consultarUltimoNumeroFactura() {
        String sql = "SELECT MAX(numero) AS max_num FROM (SELECT numero FROM "
                + "FacturaPelicula UNION ALL SELECT numero FROM FacturaSnacks)";
        ConexionSQL conector = new ConexionSQL();
        try (Connection conn = conector.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("max_num");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar último número de factura: " + e.getMessage());
        }
        return 0;
    }

    public ArrayList<String[]> cargarAsientosOcupados() {
        ArrayList<String[]> asientosOcupados = new ArrayList<>();
        String sql = "SELECT sala, asientos FROM FacturaPelicula";
        ConexionSQL conector = new ConexionSQL();
        try (Connection conn = conector.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String sala = rs.getString("sala");
                String asientosStr = rs.getString("asientos");
                if (asientosStr != null && !asientosStr.isEmpty()) {
                    String[] asientosArray = asientosStr.split(", ");
                    for (String asiento : asientosArray) {
                        asientosOcupados.add(new String[]{sala, asiento});
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar asientos ocupados: " + e.getMessage());
        }
        return asientosOcupados;
    }

    public void guardarFacturaPelicula(FacturaPelicula factura) {
        String sql = "INSERT INTO FacturaPelicula(numero, cliente, email, telefono, "
                + "pelicula, asientos, sala, horario, cantidadBoletos, precioUnitario, subTotal, descuento, total) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ConexionSQL conector = new ConexionSQL();
        try (Connection conn = conector.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn != null) {
                pstmt.setInt(1, factura.getNumero());
                pstmt.setString(2, factura.getCliente().getNombre());
                pstmt.setString(3, factura.getCliente().getEmail());
                pstmt.setString(4, factura.getCliente().getTelefono());
                pstmt.setString(5, factura.getBoletos().get(0).getFuncion().getPelicula().getTitulo());
                pstmt.setString(6, factura.getDetallesAsientos());
                pstmt.setString(7, factura.getBoletos().get(0).getFuncion().getSala().getNombre());
                pstmt.setString(8, factura.getBoletos().get(0).getFuncion().getHorario().getHora());
                pstmt.setInt(9, factura.getBoletos().size());
                pstmt.setDouble(10, factura.getPrecioUnitario());
                pstmt.setDouble(11, factura.getSubtotal());
                pstmt.setDouble(12, factura.getDescuento());
                pstmt.setDouble(13, factura.getTotal());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar la factura de película: " + e.getMessage());
        }
    }

    public void guardarFacturaSnack(FacturaSnack factura) {
        String sql = "INSERT INTO FacturaSnacks(numero, cliente, email, telefono, "
                + "snack, precioUnitario, subTotal, descuento, total) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        ConexionSQL conector = new ConexionSQL();
        try (Connection conn = conector.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn != null) {
                pstmt.setInt(1, factura.getNumero());
                pstmt.setString(2, factura.getCliente().getNombre());
                pstmt.setString(3, factura.getCliente().getEmail());
                pstmt.setString(4, factura.getCliente().getTelefono());
                pstmt.setString(5, factura.snackDetalles());
                pstmt.setDouble(6, factura.getPrecioUnitario());
                pstmt.setDouble(7, factura.getSubtotal());
                pstmt.setDouble(8, factura.getDescuento());
                pstmt.setDouble(9, factura.getTotal());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar la factura de snack: " + e.getMessage());
        }
    }
    
    public ArrayList<String> consultarRegistroFacturas() {
        ArrayList<String> registro = new ArrayList<>();
        String sql = "SELECT numero, cliente, total, pelicula AS item FROM FacturaPelicula "
                + "UNION ALL SELECT numero, cliente, total, snack AS item FROM FacturaSnacks ORDER BY numero";
        ConexionSQL conector = new ConexionSQL();
        try (Connection conn = conector.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                registro.add(String.format("Factura Nro: %05d | Cliente: %-20s | Total: $%-8.2f | Compra: %s",
                        rs.getInt("numero"),
                        rs.getString("cliente"),
                        rs.getDouble("total"),
                        rs.getString("item")));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar registro de facturas: " + e.getMessage());
        }
        return registro;
    }
}