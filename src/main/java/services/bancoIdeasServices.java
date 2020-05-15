package services;

import java.util.List;
import entities.Usuario;

public interface bancoIdeasServices {

    /**
     * Crear usuario
     * @return la lista de comentarios
     * @throws ServicesException
     */
    public void createUsuario(Usuario Usuario) throws ServicesException;

    /**
     * Consultar un usuarios por medio del correo
     * @return usuario especifico
     * @throws ServicesException
     */
    public Usuario getUsuario(String correo) throws ServicesException;

    /**
     * Consultar todos los usuarios
     * @return la lista de comentarios
     * @throws ServicesException
     */
    public List<Usuario> listUsuarios() throws ServicesException;

}