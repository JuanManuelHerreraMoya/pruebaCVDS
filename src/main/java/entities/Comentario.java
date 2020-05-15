package entities;

public class Comentario {
    private int id;
    private String textoComentario;
    private int idIniciativa;
    private int idUsuario;

    public Comentario(String textoComentario, int idIniciativa, int idUsuario) {
        this.textoComentario = textoComentario;
        this.idIniciativa = idIniciativa;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public int getIdIniciativa() {
        return idIniciativa;
    }

    public void setIdIniciativa(int idIniciativa) {
        this.idIniciativa = idIniciativa;
    }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Comentario{" +
                "textoComentario='" + textoComentario + '\'' +
                ", idIniciativa=" + idIniciativa +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
