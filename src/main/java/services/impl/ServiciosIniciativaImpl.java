package services.impl;

import com.google.inject.Inject;
import entities.Comentario;
import entities.Iniciativa;
import entities.Usuario;
import exceptions.PersistenceException;
import exceptions.ServicesException;
import exceptions.ServiciosUsuarioException;
import persistence.ComentarioDAO;
import persistence.IniciativaDAO;
import services.ServiciosIniciativa;

import java.util.List;

public class ServiciosIniciativaImpl implements ServiciosIniciativa {

    @Inject
    private IniciativaDAO iniciativaDAO;

    @Override
    public int insertIniciativa(Iniciativa iniciativa) throws ServicesException {
        try {
            return iniciativaDAO.insertIniciativa(iniciativa);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al insertar iniciativa", e);
        }
    }


    @Override
    public List<Iniciativa> getIniciativas(String palabraClave) throws ServicesException {
        List<Iniciativa> iniciativas;
        try {
            iniciativas = iniciativaDAO.getIniciativas(palabraClave);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al consultar a las iniciativas", e);
        }
        return iniciativas;
    }

    @Override
    public Iniciativa getIniciativaId(int id) throws ServicesException {
        Iniciativa iniciativa=null;
        try {
            iniciativa = iniciativaDAO.getIniciativaId(id);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al consultar la iniciativa con id: "+id, e);
        }
        return iniciativa;
    }

    @Override
    public Iniciativa getIniciativaNombre(String nombreIniciativa) throws ServicesException {
        Iniciativa iniciativa=null;
        try {
            iniciativa = iniciativaDAO.getIniciativaNombre(nombreIniciativa);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al consultar la iniciativa con nombre: "+nombreIniciativa, e);
        }
        return iniciativa;
    }

    @Override
    public List<Iniciativa> getIniciativas() throws ServicesException {
        List<Iniciativa> iniciativas;
        try {
            iniciativas = iniciativaDAO.getIniciativas();
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al consultar a las iniciativas", e);
        }
        return iniciativas;
    }

    @Override
    public void updateIniciativa(String nombre, String estado) throws ServicesException {
        try{
            iniciativaDAO.updateIniciativa(nombre, estado);
        } catch (PersistenceException e) {
            throw new ServicesException("Error al actualizar la iniciativa:"+nombre, e);
        }
    }

    @Override
    public void updateVotosIniciativa(String nombre, int numerovotos) throws ServicesException {
        try{
            iniciativaDAO.updateVotosIniciativa(nombre, numerovotos);
        } catch (PersistenceException e) {
            throw new ServicesException("Error al actualizar los votos de la iniciativa:"+nombre, e);
        }
    }

    @Override
    public List<Iniciativa> getIniciativaProponente(String nombreusuario) throws ServicesException {
        List<Iniciativa> iniciativas =null;
        try {
            iniciativas = iniciativaDAO.getIniciativaProponente(nombreusuario);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al consultar las iniciativas del proponente: "+nombreusuario, e);
        }
        return iniciativas;
    }

    @Override
    public void updateIniciativaDesc(int id, String descripcionIniciativa) throws ServicesException {
        try {
            iniciativaDAO.updateIniciativaDesc(id, descripcionIniciativa);
        } catch (Exception e) {
            throw new ServicesException("Error al actualizar la descripcion de la iniciativa:"+id, e);
        }
    }

    @Override
    public void updateIniciativaNombre(int id, String nombre) throws ServicesException {
        try {
            iniciativaDAO.updateIniciativaNombre(id, nombre);
        } catch (Exception e) {
            throw new ServicesException("Error al actualizar el nombre de la iniciativa:"+id, e);
        }
    }

    @Override
    public void updateIniciativaPalabrasC(int id, String palabrasclave) throws ServicesException {
        try {
            iniciativaDAO.updateIniciativaPalabrasC(id, palabrasclave);
        } catch (Exception e) {
            throw new ServicesException("Error al actualizar las palabras clave de la iniciativa:"+id, e);
        }
    }

    @Override
    public void updateIniciativaRelacionada(int id, int idIniciativaRelacionada) throws ServicesException {
        try {
            iniciativaDAO.updateIniciativaRelacionada(id, idIniciativaRelacionada);
            iniciativaDAO.updateIniciativaRelacionada(idIniciativaRelacionada, id);
        } catch (Exception e) {
            throw new ServicesException("Error al actualizar la iniciativa relacionada: "+id, e);
        }
    }

    @Override
    public List<Iniciativa> getIniciativasEst(String estado) throws ServicesException {
        try {
            return iniciativaDAO.getIniciativasEst(estado);
        } catch (Exception e) {
            throw new ServicesException("Error al consultar las inciativas con el estado:"+estado, e);
        }
    }

    @Override
    public List<Integer> getEstadisitica() throws ServicesException {
        try{
            return iniciativaDAO.getEstadistica();
        }catch (Exception e) {
            throw new ServicesException("Error al calcular las estadisticas", e);
        }
    }
}
