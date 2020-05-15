package services.impl;

import com.google.inject.Inject;
import entities.Comentario;
import entities.Iniciativa;
import persistence.ComentarioDAO;
import exceptions.PersistenceException;
import exceptions.ServicesException;
import services.ServiciosComentario;

import java.util.List;

public class ServiciosComentarioImpl implements ServiciosComentario {

    @Inject
    ComentarioDAO comentarioDAO;
    
    @Override
    public void insertComentario(Comentario comentario) throws ServicesException {
        try {
            comentarioDAO.insertComentario(comentario);
        }
        catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException("Error al insertar comentario", e);
        }
    }

    @Override
    public List<Comentario> getComentarios(int idIniciativa) throws ServicesException {
        List<Comentario> comentarios;
        try {
            comentarios = comentarioDAO.getComentarios(idIniciativa);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al consultar a los comentarios", e);
        }
        return comentarios;
    }
}
