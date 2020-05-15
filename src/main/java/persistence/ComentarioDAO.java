package persistence;

import entities.Comentario;
import exceptions.PersistenceException;
import java.util.List;

public interface ComentarioDAO {
    public void insertComentario(Comentario comentario) throws PersistenceException;
    public List<Comentario> getComentarios(int idIniciativa) throws PersistenceException;
}
