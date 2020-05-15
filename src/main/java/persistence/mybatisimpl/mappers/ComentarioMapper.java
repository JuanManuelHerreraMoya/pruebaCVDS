package persistence.mybatisimpl.mappers;

import entities.Comentario;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComentarioMapper {
    public void insertComentario(Comentario comentario);
    public List<Comentario> getComentarios(@Param("idIniciativa")int idIniciativa);
}
