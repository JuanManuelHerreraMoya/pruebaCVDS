package services;

import entities.Comentario;
import exceptions.ServicesException;

import java.util.List;

public interface ServiciosComentario {
    public void insertComentario(Comentario comentario) throws ServicesException;
    public List<Comentario> getComentarios(int idIniciativa) throws ServicesException;
    //public void deleteComentario(int idComentario, int idUsuario, int idIniciativa) throws ServicesException;
    
}
