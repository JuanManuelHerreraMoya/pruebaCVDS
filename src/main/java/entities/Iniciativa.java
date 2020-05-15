package entities;
import java.util.Date;
import java.util.List;

public class Iniciativa {
    private String nombre;
    private int id;
    private String estado;
    private int numerovotos;
    private String descripcion;
    private String palabrasclave;
    private String nombreusuario;
    private String correousuario;
    private int idIniciativaRelacionada;
    private Date fecha;
    private List<Comentario> comentarioList;
    //Iniciativa("pruebaDosBien","En espera de revisión",1,"prueba de que funciona","prueba","santi","san@gmail.com");


    public Iniciativa(String nombre, String estado, int numerovotos, String descripcion, String palabrasclave, String nombreusuario, String correousuario) {
        this.nombre = nombre;
        this.estado = estado;
        this.numerovotos = numerovotos;
        this.descripcion = descripcion;
        this.palabrasclave = palabrasclave;
        this.nombreusuario = nombreusuario;
        this.correousuario = correousuario;
    }

    public Iniciativa(String nombre, String estado, int numerovotos, String descripcion, String palabrasclave, String nombreusuario, String correousuario, int idIniciativaRelacionada) {
        this.nombre = nombre;
        this.estado = estado;
        this.numerovotos = numerovotos;
        this.descripcion = descripcion;
        this.palabrasclave = palabrasclave;
        this.nombreusuario = nombreusuario;
        this.correousuario = correousuario;
        this.idIniciativaRelacionada = idIniciativaRelacionada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroVotos() {
        return numerovotos;
    }

    public void setNumeroVotos(int numerovotos) {
        this.numerovotos = numerovotos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPalabrasClave() {
        return palabrasclave;
    }

    public void setPalabrasClave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

    public String getNombreUsuario() {
        return nombreusuario;
    }

    public void setNombreUsuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getCorreoUsuario() {
        return correousuario;
    }

    public void setCorreoUsuario(String correousuario) {
        this.correousuario = correousuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public int getIdIniciativaRelacionada() {
        return idIniciativaRelacionada;
    }

    public void setIdIniciativaRelacionada(int idIniciativaRelacionada) {
        this.idIniciativaRelacionada = idIniciativaRelacionada;
    }

    @Override
    public String toString() {
        return "Iniciativa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", numeroVotos=" + numerovotos +
                ", descripcion='" + descripcion + '\'' +
                ", palabrasClave='" + palabrasclave + '\'' +
                ", nombreUsuario='" + nombreusuario + '\'' +
                ", correoUsuario='" + correousuario + '\'' +
                ", fecha=" + fecha + '}';
    }
    //Iniciativa("pruebaDosBien","En espera de revisión",1,"prueba de que funciona","prueba","santi","san@gmail.com");
    public Iniciativa(int id, String nombre, String estado, int numeroVotos, String descripcion, String palabrasClave, String nombreusuario, String correoUsuario, int idIniciativaRelacionada) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.numerovotos = numeroVotos;
        this.descripcion = descripcion;
        this.palabrasclave = palabrasClave;
        this.nombreusuario = nombreusuario;
        this.correousuario = correoUsuario;
        this.idIniciativaRelacionada = idIniciativaRelacionada;
    }
}
