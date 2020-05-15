package services.impl;

import com.google.inject.Inject;
import entities.Voto;
import exceptions.PersistenceException;
import exceptions.ServicesException;
import persistence.VotoDAO;
import services.ServiciosVoto;

public class ServiciosVotoImpl implements ServiciosVoto {

    @Inject
    private VotoDAO votoDAO;

    @Override
    public void insertVoto(Voto voto) throws ServicesException {
        try {
            votoDAO.insertVoto(voto);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al insertar voto", e);
        }
    }

    @Override
    public void getVoto(int idUsuario, int idIniciativa) throws ServicesException {
        try {
            votoDAO.getVoto(idUsuario, idIniciativa);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al obtener voto", e);
        }
    }

    @Override
    public void deleteVoto(int idUsuario, int idIniciativa) throws ServicesException {
        try {
            votoDAO.deleteVoto(idUsuario, idIniciativa);
        }
        catch (PersistenceException e) {
            throw new ServicesException("Error al borrar voto", e);
        }
    }
}
