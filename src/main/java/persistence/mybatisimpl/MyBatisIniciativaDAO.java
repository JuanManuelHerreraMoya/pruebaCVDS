package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.Iniciativa;
import exceptions.PersistenceException;
import persistence.IniciativaDAO;
import persistence.mybatisimpl.mappers.IniciativaMapper;


import java.util.List;

public class MyBatisIniciativaDAO implements IniciativaDAO {
    @Inject
    IniciativaMapper iniciativaMapper;
    @Override
    public int insertIniciativa(Iniciativa iniciativa) throws PersistenceException {
        try {
            return iniciativaMapper.insertIniciativa(iniciativa);
        }catch (Exception e){
            throw new PersistenceException("Save error iniciativa");
        }
    }

    @Override
    public List<Iniciativa> getIniciativas(String palabraClave) throws PersistenceException {
        try {
            return iniciativaMapper.getIniciativasPaClave(palabraClave);
        }catch (Exception e){
            throw new PersistenceException("Load error iniciativa str palabras clave");
        }
    }

    @Override
    public Iniciativa getIniciativaId(int id) throws PersistenceException {
        Iniciativa iniciativa = iniciativaMapper.getIniciativaId(id);
        if(iniciativa==null) throw new PersistenceException("Error al cargar la iniciativa id:"+id+"No esxiste ninguna con ese id");
        else return iniciativa;
    }

    @Override
    public Iniciativa getIniciativaNombre(String nombreIniciativa) throws PersistenceException {
        Iniciativa iniciativa = iniciativaMapper.getIniciativaNombre(nombreIniciativa);
        if(iniciativa==null) throw new PersistenceException("Error al cargar la iniciativa nombre:"+nombreIniciativa+"No esxiste ninguna con ese nombre");
        else return iniciativa;
    }

    @Override
    public List<Iniciativa> getIniciativas() throws PersistenceException {
        try {
            return iniciativaMapper.getIniciativasAll();
        }catch (Exception e){
            throw new PersistenceException("Load error iniciativa todas, sin parametro");
        }
    }

    @Override
    public void updateIniciativa(String nombre, String estado) throws PersistenceException {
        try {
             iniciativaMapper.updateIniciativa(nombre, estado);
        }catch (Exception e){
            throw new PersistenceException("Update error iniciativa nombre");
        }
    }

    @Override
    public void updateVotosIniciativa(String nombre, int numerovotos) throws PersistenceException {
        try {
            iniciativaMapper.updateVotosIniciativa(nombre, numerovotos);
        }catch (Exception e){
            throw new PersistenceException("Update error iniciativa votos");
        }
    }

    @Override
    public List<Iniciativa> getIniciativaProponente(String nombreusuario) throws PersistenceException {
        try {
            return iniciativaMapper.getIniciativaProponente(nombreusuario);
        }catch (Exception e){
            throw new PersistenceException("Get error iniciativa");
        }
    }

    @Override
    public void updateIniciativaDesc(int id, String descripcionIniciativa) throws PersistenceException {
        try {
            iniciativaMapper.updateIniciativaDesc(id, descripcionIniciativa);
        } catch (Exception e) {
            throw new PersistenceException("Update error iniciativa descripcion");
        }
    }

    @Override
    public void updateIniciativaNombre(int id, String nombre) throws PersistenceException {
        try {
            iniciativaMapper.updateIniciativaNombre(id, nombre);
        }catch (Exception e) {
            throw new PersistenceException("Update error iniciativa nombre");
        }
    }

    @Override
    public void updateIniciativaPalabrasC(int id, String palabrasclave) throws PersistenceException {
        try {
            iniciativaMapper.updateIniciativaPalabrasC(id, palabrasclave);
        }catch (Exception e) {
            throw new PersistenceException("Update error iniciativa palabras clave");
        }
    }

    @Override
    public void updateIniciativaRelacionada(int id, int idIniciativaRelacionada) throws PersistenceException {
        try {
            iniciativaMapper.updateIniciativaRelacionada(id, idIniciativaRelacionada);
        }catch (Exception e) {
            throw new PersistenceException("Update error iniciativa relacionada");
        }
    }

    @Override
    public List<Iniciativa> getIniciativasEst(String estado) throws PersistenceException {
        try {
            return iniciativaMapper.getIniciativasEst(estado);
        } catch (Exception e) {
            throw new PersistenceException("Get error iniciativa estado");
        }
    }

    @Override
    public List<Integer> getEstadistica() throws PersistenceException {
        try {
            return iniciativaMapper.getEstadistica();
        } catch (Exception e) {
            throw new PersistenceException("Get error estadisticas");
        }
    }
}
