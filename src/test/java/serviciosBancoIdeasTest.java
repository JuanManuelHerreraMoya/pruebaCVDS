import entities.Comentario;
import entities.Iniciativa;
import entities.Usuario;
import entities.Voto;
import exceptions.ServicesException;
import exceptions.ServiciosUsuarioException;
import org.junit.Test;
import services.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class serviciosBancoIdeasTest {
    private ServiciosUsuario serviciosUsuario;
    private ServiciosIniciativa serviciosIniciativa;
    private ServiciosVoto serviciosVoto;
    private ServiciosComentario serviciosComentario;

    public serviciosBancoIdeasTest() throws ServicesException {
        serviciosUsuario = bancoIdeasServicesFactory.getInstance().getUsuarioTesting();
        serviciosIniciativa = bancoIdeasServicesFactory.getInstance().InsertarIniciativaTesting();
        serviciosVoto = bancoIdeasServicesFactory.getInstance().getVotoTesting();
        serviciosComentario = bancoIdeasServicesFactory.getInstance().getComentario();
    }

    /**
     * Esta prueba utiliza el servicio consultar recurso y verificamos que el objeto
     * que asignamos no este vacio.
     *
     * @throws ServiciosUsuarioException
     */
    @Test
    public void deberiaConsultarUsuario() {
        try {
            Usuario usrPrueba = serviciosUsuario.consultarUsuario("santiago@gmail.com");
            assertTrue(usrPrueba != null);
            System.out.println("funciona 1");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaConsultarUsuarios() {
        try {
            List<Usuario> usrPrueba = serviciosUsuario.consultarUsuarios();
            assertTrue(usrPrueba != null);
            System.out.println("funciona 2");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaActualizarUsuarios() {
        Usuario userpr = new Usuario(8, "laura", "123456789", "laura@gmail.com", "Admin", "Finanzas");
        try {
            serviciosUsuario.insertarUsuario(userpr);
            serviciosUsuario.updateRolUsuario(userpr.getId(), "Admin");
            Usuario user = serviciosUsuario.consultarUsuario("laura@gmail.com");
            assertTrue("Admin".equals(user.getTipoUser()));
            //System.out.println(serviciosUsuario.consultarUsuario("laura@gmail.com"));
            System.out.println("funciona 3");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaInsertarUsuarios() {
        Usuario userpr = new Usuario("laura2", "1234567", "laura2@gmail.com", "Admin", "Finanzas");
        try {
            serviciosUsuario.insertarUsuario(userpr);
            assertTrue(serviciosUsuario.consultarUsuario("laura2@gmail.com") != null);
            //System.out.println(serviciosUsuario.consultarUsuario("laura2@gmail.com"));
            System.out.println("funciona 4");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaConsultarIniciativas() {
        try {
            List<Iniciativa> iniciativasPrueba = serviciosIniciativa.getIniciativas();
            assertTrue(iniciativasPrueba != null);
            //System.out.println(iniciativasPrueba);
            System.out.println("funciona 5");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaInsertarIniciativa(){
        Iniciativa iniciativaPrr = new Iniciativa("pruebaDeInsersion", "En revisión", 1,"prueba de que funciona", "prueba", "Santiago", "santiago@gmail.com");
        try {
            serviciosIniciativa.insertIniciativa(iniciativaPrr);
            assertTrue(serviciosIniciativa.getIniciativas() != null);
            //System.out.println(serviciosIniciativa.getIniciativas());
            System.out.println("funciona 6");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaActualizarIniciativa() throws ServicesException {
        Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
        serviciosIniciativa.updateIniciativa(iniciativapr.getNombre(), "En revisión");
        try {
            Iniciativa ini = serviciosIniciativa.getIniciativaId(iniciativapr.getId());
            assertTrue("En revisión".equals(ini.getEstado()));
            //System.out.println(iniciativapr);
            System.out.println("funciona 7");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void deberiaActualizarVotosIniciativa() throws ServicesException {
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            Usuario usuario = serviciosUsuario.consultarUsuario("juan@gmail.com");
            Voto voto = new Voto(usuario.getId(), iniciativapr.getId());
            serviciosIniciativa.updateVotosIniciativa(iniciativapr.getNombre(), iniciativapr.getNumeroVotos()+1);
            serviciosVoto.insertVoto(voto);
            Iniciativa ini = serviciosIniciativa.getIniciativaId(iniciativapr.getId());
            int n = ini.getNumeroVotos();
            assertTrue(iniciativapr.getNumeroVotos()+1==n);
            System.out.println("funciona 8");
        } catch (Exception e) {
            System.out.println("fallas");
        }
    }

    @Test
    public void deberiaAgregarUnComentario(){
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            Usuario usuario = serviciosUsuario.consultarUsuario("juan@gmail.com");
            //Comentario comentario = new Comentario("hola, ya funciona", iniciativapr.getId(), usuario.getId());
            serviciosComentario.insertComentario(new Comentario("hola, ya funciona completo", iniciativapr.getId(), usuario.getId()));
            serviciosComentario.insertComentario(new Comentario("Segundo comentario bien", iniciativapr.getId(), usuario.getId()));
            System.out.println("funciona 9");
        } catch (ServicesException | ServiciosUsuarioException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaConsultarComentario(){
        try {
            List<Comentario> comentarios = serviciosComentario.getComentarios(2);
            System.out.println("funciona 10");
        } catch (ServicesException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaBuscarIniciativaPorProponente(){
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            Usuario usuario = serviciosUsuario.consultarUsuario("juan@gmail.com");
            List<Iniciativa> iniciativas = serviciosIniciativa.getIniciativaProponente(usuario.getNombre());
            System.out.println("funciona 11");
        } catch (ServicesException | ServiciosUsuarioException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaActualizarIniciativaDesc() {
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            serviciosIniciativa.updateIniciativaDesc(iniciativapr.getId(), "Se actualizo la descripcion");
            Iniciativa ini = serviciosIniciativa.getIniciativaId(iniciativapr.getId());
            assertTrue("Se actualizo la descripcion".equals(ini.getDescripcion()));
            System.out.println("funciona 12");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaActualizarIniciativaNombre() {
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            serviciosIniciativa.updateIniciativaNombre(iniciativapr.getId(), "Nuevo nombre");
            Iniciativa ini = serviciosIniciativa.getIniciativaId(iniciativapr.getId());
            assertTrue("Nuevo nombre".equals(ini.getNombre()));
            System.out.println("funciona 13");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaActualizarIniciativaPalabrasClave() {
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            serviciosIniciativa.updateIniciativaPalabrasC(iniciativapr.getId(), "Nueva palabra clave");
            Iniciativa ini = serviciosIniciativa.getIniciativaId(iniciativapr.getId());
            assertTrue("Nueva palabra clave".equals(ini.getPalabrasClave()));
            System.out.println("funciona 14");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaBuscarIniciativaPorEstado() {
        try {
            List<Iniciativa> iniciativas = serviciosIniciativa.getIniciativasEst("En espera de revisión");
            System.out.println(iniciativas);
            System.out.println("funciona 15");
        } catch (ServicesException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaRelacionarIniciativa() {
        try {
            Iniciativa iniciativapr = serviciosIniciativa.getIniciativaId(1);
            serviciosIniciativa.updateIniciativaRelacionada(iniciativapr.getId(), 2);
            iniciativapr = serviciosIniciativa.getIniciativaId(1);
            Iniciativa ini = serviciosIniciativa.getIniciativaId(2);
            //assertTrue("Nueva palabra clave".equals(ini.getPalabrasClave()));
            System.out.println("id :"+iniciativapr.getId()+"id rela: "+iniciativapr.getIdIniciativaRelacionada());
            System.out.println("id :"+ini.getId()+"id rela: "+ini.getIdIniciativaRelacionada());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // @Test
    // public void deberiaFiltrarRecursosPorId() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.recursoPorId(116);

    // assertTrue (recurPrueba !=null);
    // }

    // @Test
    // public void deberiaFiltrarRecursosDisponibles() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.recursosDisponibles();

    // assertTrue (recurPrueba !=null);
    // }

    // @Test
    // public void deberiaFiltrarRecursosPorNombre() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.filtrarNombre("e");

    // //for(Recurso r: recurPrueba) System.out.println(r);

    // assertTrue (recurPrueba !=null);
    // }

    // @Test
    // public void deberiaFiltrarRecursosPorUbicacion() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.filtrarUbicacion("e");

    // //for(Recurso r: recurPrueba) System.out.println(r);

    // assertTrue (recurPrueba !=null);
    // }

    // @Test
    // public void cambiarEstadoMatenimiento() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.recursoPorId(102);

    // boolean testigo = recurPrueba.get(0).isAveriado();

    // bibliotecaS.cambiarEstadoMatenimiento(recurPrueba.get(0).getId());

    // recurPrueba = bibliotecaS.recursoPorId(102);

    // boolean testigo2 = recurPrueba.get(0).isAveriado();

    // assertTrue (testigo != testigo2);
    // }

    // @Test
    // public void deberiaFiltrarRecursosPorCapacidad() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.filtrarCapacidad(5);
    // //for(Recurso r:recurPrueba) System.out.println(r);
    // assertTrue (recurPrueba !=null);
    // }

    // @Test
    // public void deberiaFiltrarRecursosPorTipo() throws ServicesException {

    // List<Recurso> recurPrueba = bibliotecaS.filtrarTipo("l");
    // //for(Recurso r:recurPrueba) System.out.println(r);
    // assertTrue (recurPrueba !=null);
    // }

}
