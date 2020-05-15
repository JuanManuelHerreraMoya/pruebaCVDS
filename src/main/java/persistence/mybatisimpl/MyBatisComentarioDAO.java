package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.Comentario;
import exceptions.PersistenceException;
import persistence.ComentarioDAO;
import persistence.mybatisimpl.mappers.ComentarioMapper;

import java.util.List;

public class MyBatisComentarioDAO implements ComentarioDAO {

    @Inject
    ComentarioMapper comentarioMapper;


    @Override
    public void insertComentario(Comentario comentario) throws PersistenceException {
        try {
            comentarioMapper.insertComentario(comentario);
        }catch (final Exception e){
            throw new PersistenceException("Save error comentario");
        }
    }

    @Override
    public List<Comentario> getComentarios(int idIniciativa) throws PersistenceException {
        try {
            return comentarioMapper.getComentarios(idIniciativa);
        }catch (final Exception e){
            throw new PersistenceException("Load error comentarios");
        }
    }
}
