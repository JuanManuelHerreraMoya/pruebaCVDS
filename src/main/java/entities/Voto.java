package entities;

public class Voto {
    private int idUsuario;
    private int idIniciativa;

    public Voto(int idUsuario, int idIniciativa) {
        this.idUsuario = idUsuario;
        this.idIniciativa = idIniciativa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdIniciativa() {
        return idIniciativa;
    }

    public void setIdIniciativa(int idIniciativa) {
        this.idIniciativa = idIniciativa;
    }
}
