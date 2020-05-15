package persistence.mybatisimpl;

import java.util.List;

import entities.Usuario;


import com.google.inject.Inject;

import exceptions.PersistenceException;
import persistence.UsuarioDAO;
import persistence.mybatisimpl.mappers.UsuarioMapper;

public class MyBatisUsuarioDAO implements UsuarioDAO {
    @Inject
    UsuarioMapper usuarioMapper;


    @Override
    public void insertUsuario(final Usuario usuario) throws PersistenceException {
        try {
            usuarioMapper.insertUsuario(usuario);
        }catch (final Exception e){
            throw new PersistenceException("Save error");
        }
    }

    @Override
    public Usuario getUsuario(final String correo) throws PersistenceException {
        final Usuario user=usuarioMapper.getUsuario(correo);
        if(user==null) throw new PersistenceException("Error al consultar cliente "+ correo+
                " - No existe");
        else return user;
    }

    @Override
    public List<Usuario> getUsuarios() throws PersistenceException {
        try {
            return usuarioMapper.getUsuarios();
        }catch (final Exception e){
            throw new PersistenceException("Load error");
        }
    }

    @Override
    public int updateRolUsuario(final int idUsuario, final String tipoUser) throws PersistenceException {
        try {
            return usuarioMapper.updateRolUsuario(idUsuario, tipoUser);
        } catch (Exception e) {
            throw new PersistenceException("Actualizacion error");
        }
    }
}
