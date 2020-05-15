import com.google.inject.Inject;
import com.google.inject.Injector;
import entities.Usuario;
import org.junit.Test;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import persistence.PersistenceException;
import persistence.UsuarioDAO;
import persistence.mybatisimpl.MyBatisUsuarioDAO;

import static com.google.inject.Guice.createInjector;

public class UsuarioTest {


   /* private Usuario usuario;
    @Inject
    private UsuarioDAO usuarioDAO;

    @Test
    public void probarInsertUsuario(){
        usuarioDAO = new MyBatisUsuarioDAO();
        usuario = new Usuario(1, "juan", "1234", "juan@gmail.com", "Admin");
        try {
            usuarioDAO.save(usuario);
        } catch (PersistenceException e) {
            System.out.println("Fallo la prueba");
            e.printStackTrace();

        }
    }*/
}
