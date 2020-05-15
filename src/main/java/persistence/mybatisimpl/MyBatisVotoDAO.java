package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.Voto;
import exceptions.PersistenceException;
import persistence.VotoDAO;
import persistence.mybatisimpl.mappers.VotoMapper;

public class MyBatisVotoDAO implements VotoDAO {

    @Inject
    VotoMapper votoMapper;

    @Override
    public void insertVoto(Voto voto) throws PersistenceException {
       try {
           votoMapper.insertVoto(voto);
       }catch (final Exception e){
           throw new PersistenceException("Save error voto");
       }
    }

    @Override
    public void getVoto(int idUsuario, int idIniciativa) throws PersistenceException {
       try {
           votoMapper.getVoto(idUsuario, idIniciativa);
       }catch (final Exception e){
           throw new PersistenceException("Get error voto");
       }
    }

    @Override
    public void deleteVoto(int idUsuario, int idIniciativa) throws PersistenceException {
       try {
           votoMapper.deleteVoto(idUsuario, idIniciativa);
       }catch (final Exception e){
           throw new PersistenceException("Delete error voto");
       }
    }
}
