package managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.google.inject.Injector;


import entities.Usuario;
import persistence.UsuarioDAO;
import services.*;


@SuppressWarnings("deprecation")
@ManagedBean(name="PageBean")
@SessionScoped
public class BasePageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Injector injector;
    private long idRec;
    private String usuario;
    private Usuario user;

    private Injector getInjector() {
        if (injector == null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();
            injector = (Injector) servletContext.getAttribute(Injector.class.getName());
        }
        return injector;
    }

    protected UsuarioDAO getUsuarioDao() {
        return getInjector().getInstance(UsuarioDAO.class);
    }

    protected ServiciosUsuario getServiciosUsuario() {
        return getInjector().getInstance(ServiciosUsuario.class);
    }

    protected ServiciosIniciativa getServiciosIniciativa() { return getInjector().getInstance(ServiciosIniciativa.class); }

    protected ServiciosVoto getServiciosVoto() { return bancoIdeasServicesFactory.getInstance().getVoto(); }

    protected ServiciosComentario getServiciosComentario() { return bancoIdeasServicesFactory.getInstance().getComentario(); }

    protected void mensajeApp(Exception e) {
        Mensajes.mensajeAplicacion(e.getMessage());
    }



    public String page01(){
        return "menu?faces-redirect=true";
    }

    public String page02(){
        return "Pro?faces-redirect=true";
    }

    public String page03(){ return "Admin?faces-redirect=true"; }

    public String page04(){
        return "PMO?faces-redirect=true";
    }

    public String page05(){
        return "User?faces-redirect=true";
    }

    public String page1(){
        return "iniciosesion?faces-redirect=true";
    }

    public String page2(){
        return "RegistrarIniciativa?faces-redirect=true";
    }

    public String page3(){
        return "RegistrarUsuario?faces-redirect=true";
    }

    public String page4(){
        return "verUsuarios?faces-redirect=true";
    }

    public String page5(){
        return "informes?faces-redirect=true";
    }

    public String page6(){
        return "ModificarUsuario?faces-redirect=true";
    }

    public String page7(){
        return "ModificarIniciativa?faces-redirect=true";
    }

    public String page8(){
        return "misIniciativas?faces-redirect=true";
    }

    public String page9(){
        return "relacionarIniciativa?faces-redirect=true";
    }

    public String page10(){
        return "filtrarEstado?faces-redirect=true";
    }

    public long getIdRec() {
        return this.idRec;
    }

    public void setIdRec(long a) {
        this.idRec=a;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setUsuario (String usuario){
        this.usuario = usuario;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}