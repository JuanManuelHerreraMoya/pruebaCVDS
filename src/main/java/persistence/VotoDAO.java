package persistence;

import entities.Voto;
import exceptions.PersistenceException;

import java.util.List;

public interface VotoDAO {
    public void insertVoto(Voto voto) throws PersistenceException;
    public void getVoto(int idUsuario, int idIniciativa) throws PersistenceException;
    public void deleteVoto(int idUsuario, int idIniciativa) throws PersistenceException;
}
