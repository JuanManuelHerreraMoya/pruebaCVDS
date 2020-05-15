package persistence.mybatisimpl.mappers;

import entities.Voto;
import org.apache.ibatis.annotations.Param;

public interface VotoMapper {
   public void insertVoto(Voto voto);
   public void getVoto(@Param("idUsuario") int idUsuario, @Param("idIniciativa") int idIniciativa);
   public void deleteVoto(@Param("idUsuario") int idUsuario, @Param("idIniciativa") int idIniciativa);
}
