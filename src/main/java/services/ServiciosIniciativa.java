package services;

import entities.Iniciativa;
import exceptions.PersistenceException;
import exceptions.ServicesException;

import java.util.List;

public interface ServiciosIniciativa {
    public int insertIniciativa(Iniciativa iniciativa) throws ServicesException;
    public List<Iniciativa> getIniciativas(String palabraClave) throws ServicesException;
    public Iniciativa getIniciativaId(int id) throws ServicesException;
    public Iniciativa getIniciativaNombre(String nombre) throws ServicesException;
    public List<Iniciativa> getIniciativas() throws ServicesException;
    public void updateIniciativa(String nombre, String estado) throws ServicesException;
    public void updateVotosIniciativa(String nombre, int numerovotos) throws ServicesException;
    public void updateIniciativaDesc(int id, String descripcionIniciativa) throws ServicesException;
    public void updateIniciativaNombre(int id, String nombre) throws ServicesException;
    public void updateIniciativaPalabrasC(int id, String palabrasclave) throws ServicesException;
    public void updateIniciativaRelacionada(int id, int idIniciativaRelacionada) throws ServicesException;
    public List<Iniciativa> getIniciativaProponente(String nombreusuario) throws ServicesException;
	public List<Iniciativa> getIniciativasEst(String estado) throws ServicesException;
	public List<Integer> getEstadisitica()throws ServicesException;

}
