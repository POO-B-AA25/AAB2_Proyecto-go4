package Controller;
import java.io.Serializable;

public class Cliente implements Serializable {
    
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;

    public Cliente(String cedula, String nombre, String telefono,String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getCedula() {
        return cedula;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getTelefono() {
        return nombre;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
