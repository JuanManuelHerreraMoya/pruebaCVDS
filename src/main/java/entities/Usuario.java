package entities;

public class Usuario {

    private int id;
    private String nombre;
    private String contrasena;
    private String correo;
    private String tipoUser;
    private String dependencia;

    public Usuario(int id, String nombre, String contrasena, String correo, String tipoUser, String dependencia) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.tipoUser = tipoUser;
        this.dependencia = dependencia;
    }
    
    public Usuario(String nombre, String contrasena, String correo, String tipoUser, String dependencia) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.tipoUser = tipoUser;
        this.dependencia = dependencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getcontrasena() {
        return contrasena;
    }

    public void setcontrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }
    
    public String getDependencia() {return dependencia; }

    public void setDependencia(String dependencia) { this.dependencia = dependencia; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", tipoUser='" + tipoUser + '\'' +
                ", dependencia='" + dependencia + '\'' +
                '}';
    }

}
