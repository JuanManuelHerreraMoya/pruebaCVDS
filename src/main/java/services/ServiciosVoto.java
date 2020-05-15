package services;

import entities.Voto;
import exceptions.ServicesException;

public interface ServiciosVoto {
    public void insertVoto(Voto voto) throws ServicesException;
    public void getVoto(int idUsuario, int idIniciativa) throws ServicesException;
    public void deleteVoto(int idUsuario, int idIniciativa) throws ServicesException;
}
