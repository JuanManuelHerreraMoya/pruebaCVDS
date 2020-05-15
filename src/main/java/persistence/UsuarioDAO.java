package persistence;

import entities.Usuario;
import exceptions.PersistenceException;

import java.util.List;

public interface UsuarioDAO {
    public void insertUsuario(Usuario usuario) throws PersistenceException;
    public Usuario getUsuario(String correo) throws PersistenceException;
    public int updateRolUsuario(int idUsuario, String tipoUser) throws PersistenceException;
    //public void remove(Usuario usuario);
    //public Usuario load(Object id) throws PersistenceException;
    public List<Usuario> getUsuarios() throws PersistenceException;
}
