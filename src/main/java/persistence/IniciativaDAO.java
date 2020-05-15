package persistence;

import entities.Iniciativa;
import exceptions.PersistenceException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IniciativaDAO {
    public int insertIniciativa(Iniciativa iniciativa) throws PersistenceException;
    public List<Iniciativa> getIniciativas(String palabraClave)  throws PersistenceException;
    public Iniciativa getIniciativaId(int id) throws PersistenceException;
    public Iniciativa getIniciativaNombre(String nombreIniciativa) throws PersistenceException;
    public List<Iniciativa> getIniciativas() throws PersistenceException;
    public void updateIniciativa(String nombre, String estado) throws PersistenceException;
    public void updateVotosIniciativa(String nombre, int numerovotos) throws PersistenceException;
    public void updateIniciativaDesc(int id, String descripcionIniciativa) throws PersistenceException;
    public void updateIniciativaNombre(int id, String nombre) throws PersistenceException;
    public void updateIniciativaPalabrasC(int id, String palabrasclave) throws PersistenceException;
    public void updateIniciativaRelacionada(int id, int idIniciativaRelacionada) throws PersistenceException;
    public List<Iniciativa> getIniciativaProponente(String nombreusuario) throws PersistenceException;
	public List<Iniciativa> getIniciativasEst(String estado) throws PersistenceException;

    List<Integer> getEstadistica()throws PersistenceException;;
}
