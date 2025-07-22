package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    private String url;
    public String dr = "\"C:\\Users\\jcvei\\OneDrive\\Escritorio\\2 CICLO "
            + "COMPUTACION\\AAB2_Proyecto-go4\\Solucion_Codigo\\CineMas-Loja\\"
            + "dataBase\\baseDatosCinemas.db\"";
    public ConexionSQL() {
        this.url = "jdbc:sqlite:dataBase/dbCinemasFacturas.db";
    }

    public Connection conectar() {
        try {
            return DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
