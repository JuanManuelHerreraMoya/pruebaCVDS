package persistence.mybatisimpl.mappers;

import entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsuarioMapper {
    public void insertUsuario(Usuario usuario);
    public Usuario getUsuario(@Param("correoUser")String correo);
    public List<Usuario> getUsuarios();
    public int updateRolUsuario(@Param("id") int id, @Param("tipouser")String tipoUser);
    public void deleteAll();
}
