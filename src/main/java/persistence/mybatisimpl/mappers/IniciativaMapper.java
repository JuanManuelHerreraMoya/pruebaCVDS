package persistence.mybatisimpl.mappers;

import entities.Iniciativa;
import exceptions.PersistenceException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IniciativaMapper {
    public int insertIniciativa(Iniciativa iniciativa);
    public List<Iniciativa> getIniciativasPaClave(@Param("palabraClave")String palabraClave);
    public Iniciativa getIniciativaId(@Param("idIniciativa")int id);
    public Iniciativa getIniciativaNombre(@Param("nombreIniciativa")String nombreIniciativa);
    public List<Iniciativa> getIniciativasAll();
    public void updateIniciativa(@Param("nombre") String nombre, @Param("estado")String estado);
    public void updateVotosIniciativa(@Param("nombre") String nombre, @Param("numerovotos")int numerovotos);
    public void updateIniciativaDesc(@Param("id") int id, @Param("descripcion")String descripcionIniciativa);
    public void updateIniciativaNombre(@Param("id") int id, @Param("nombre")String nombre);
    public void updateIniciativaPalabrasC(@Param("id") int id, @Param("palabrasclave")String palabrasclave);
    public void updateIniciativaRelacionada(@Param("id") int id, @Param("idIniciativaRelacionada") int idIniciativaRelacionada);
    public List<Iniciativa> getIniciativaProponente(@Param("nombreusuario") String nombreusuario);
	public List<Iniciativa> getIniciativasEst(@Param("estado")String estado);
    public List<Integer> getEstadistica();
}

