package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import persistence.*;
import persistence.mybatisimpl.*;
import services.ServiciosIniciativa;
import services.ServiciosUsuario;
import services.impl.ServiciosIniciativaImpl;
import services.impl.ServiciosUsuarioImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class GuiceContextListener implements ServletContextListener {

    /**
     * Método del guice.
     * @param servletContextEvent Argumentos del programa.
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    /**
     * Método del guice.
     * @param servletContextEvent Argumentos del programa.
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId("development");
                setClassPathResource("bd-config.xml");

                // TODO Add service class associated to Stub implementation
                bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
                bind(IniciativaDAO.class).to(MyBatisIniciativaDAO.class);
                bind(ComentarioDAO.class).to(MyBatisComentarioDAO.class);
                //
                bind(ServiciosUsuario.class).to(ServiciosUsuarioImpl.class);
                bind(ServiciosIniciativa.class).to(ServiciosIniciativaImpl.class);
            }
        });
        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}