package managedbeans;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import entities.Usuario;
import exceptions.ServiciosUsuarioException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import services.ServiciosUsuario;


@ManagedBean(name = "LoginBean")
@SessionScoped

public class UsuarioBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{PageBean}")
    private BasePageBean baseBean;
    private String usuarioCorreo;
    private String password;
    private boolean rememberMe;
    private boolean noLogged;
    private ServiciosUsuario serviciosUsuario;
    private Usuario usuario;
    private Usuario usuarioConsultado;
    private String rolUsuario;
    private String nombreUsuario;
    private String nuevoUsuarioNombre;
    private String nuevoUsuarioContraseña;
    private String nuevoUsuarioCorreo;
    private String nuevoUsuarioTipoUser;
    private String nuevoUsuarioDependencia;
    private String actualizarUsuarioCorreo;
    private String changeTipoUser;

    private void convertir(String tipo){
        try {
            serviciosUsuario.updateRolUsuario(usuarioConsultado.getId(), tipo);
            usuarioConsultado = serviciosUsuario.consultarUsuario(usuarioConsultado.getCorreo());
            actualizarUsuarioCorreo="";
        } catch (ServiciosUsuarioException e) {
            this.baseBean.mensajeApp(e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario no encontrado", "Este usuario no se encuentra en nuestra base de datos"));
        }
    }

    public List<Usuario> getUsuarios(){
        List<Usuario> usuarios = null;
        try {
            usuarios = serviciosUsuario.consultarUsuarios();
        } catch (ServiciosUsuarioException e) {
            this.baseBean.mensajeApp(e);
        }
        return usuarios;
    }

    public void verUsuario(String correoUsuario){
        try {
            usuarioConsultado = serviciosUsuario.consultarUsuario(correoUsuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/ModificarUsuario.xhtml");
        } catch (IOException | ServiciosUsuarioException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void convertirTipoUser() throws ServiciosUsuarioException{
        if(changeTipoUser !=null && !changeTipoUser.equals("")) {
            convertir(changeTipoUser);
        }else {
            throw new ServiciosUsuarioException("Fallo al cambiar tipo de usuario");
        }
    }

    public void login() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            session.setAttribute("correo", "password");
            UsernamePasswordToken token = new UsernamePasswordToken(usuarioCorreo, password);
            currentUser.login(token);
            token.setRememberMe(true);
            if (currentUser.isAuthenticated()){
                serviciosUsuario = baseBean.getServiciosUsuario();
                setUsuario(serviciosUsuario.consultarUsuario(usuarioCorreo));
                setNombreUsuario(usuario.getNombre());
                setRolUsuario(usuario.getTipoUser());
                setUsuarioCorreo(usuario.getCorreo());
                baseBean.setUser(usuario);
                direccionarPorPerfil();
            }
        } catch (UnknownAccountException e) {
           this.baseBean.mensajeApp(e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario no encontrado", "Este usuario no se encuentra en nuestra base de datos"));
        }

        catch (IncorrectCredentialsException e) {
            this.baseBean.mensajeApp(e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Contraseña incorrecta", "La contraseña ingresada no es correcta"));

        } catch (ServiciosUsuarioException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void direccionarPorPerfil(){
        if (rolUsuario.equals("Admin")){
            redirectTo("/faces/Admin.xhtml");
        }
        else if (rolUsuario.equals("PMO")){
            redirectTo("/faces/PMO.xhtml");
        }
        else if (rolUsuario.equals("Proponente")){
            redirectTo("/faces/Pro.xhtml");
        }
        else if (rolUsuario.equals("User")){
            redirectTo("/faces/User.xhtml");
        }
    }

    public boolean isNoLogged() {
        return noLogged;
    }

    public void setNoLogged(boolean noLogged) {
        this.noLogged = noLogged;
    }

    public void logout() {
        try {
            if(getUser().isAuthenticated()) {
                getUser().logout();

                redirectTo("/faces/iniciosesion.xhtml");

            }
        }
        catch(Exception e) {
            this.baseBean.mensajeApp(e);

        }

    }

    public void redirectToMenu(){
        if(getUser()!=null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/menu.xhtml");
            } catch (IOException e) {
                this.baseBean.mensajeApp(e);
            }
        }

    }
	
	public void redirectToAdmin(){
        if(getUser()!=null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/Admin.xhtml");
            } catch (IOException e) {
                this.baseBean.mensajeApp(e);
            }
        }

    }
	public void redirectToPMO(){
        if(getUser()!=null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/PMO.xhtml");
            } catch (IOException e) {
                this.baseBean.mensajeApp(e);
            }
        }

    }
	public void redirectToPro(){
        if(getUser()!=null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/Pro.xhtml");
            } catch (IOException e) {
                this.baseBean.mensajeApp(e);
            }
        }

    }
	public void redirectToUser(){
        if(getUser()!=null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("user?faces-redirect=true");
            } catch (IOException e) {
                this.baseBean.mensajeApp(e);
            }
        }

    }

    public void isLogged(){
        Subject subject = SecurityUtils.getSubject();
        if ((subject.getSession().getAttribute("correo") != null) && subject.getSession().getAttribute("correo")!="NoRegistrado"){
            //redirectToMenu();
        }
        else{
            usuarioCorreo = null;
            password = null;
        }
    }

    public void redirectTo(String path){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path);
        } catch (IOException e) {
            this.baseBean.mensajeApp(e);
        }
    }



    //Gets and Sets
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.usuarioCorreo;
    }

    public void setUserName(String userName) {
        this.usuarioCorreo = userName;
    }

    private Subject getUser() {
        return SecurityUtils.getSubject();
    }

    public BasePageBean getBaseBean() {
        return this.baseBean;
    }

    public void setBaseBean(BasePageBean bs){
        this.baseBean = bs;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getNuevoUsuarioNombre() {
        return nuevoUsuarioNombre;
    }

    public void setNuevoUsuarioNombre(String nuevoUsuarioNombre) {
        this.nuevoUsuarioNombre = nuevoUsuarioNombre;
    }

    public String getNuevoUsuarioContraseña() {
        return nuevoUsuarioContraseña;
    }

    public void setNuevoUsuarioContraseña(String nuevoUsuarioContraseña) {
        this.nuevoUsuarioContraseña = nuevoUsuarioContraseña;
    }

    public String getNuevoUsuarioCorreo() {
        return nuevoUsuarioCorreo;
    }

    public void setNuevoUsuarioCorreo(String nuevoUsuarioCorreo) {
        this.nuevoUsuarioCorreo = nuevoUsuarioCorreo;
    }

    public String getNuevoUsuarioTipoUser() {
        return nuevoUsuarioTipoUser;
    }

    public void setNuevoUsuarioTipoUser(String nuevoUsuarioTipoUser) {
        this.nuevoUsuarioTipoUser = nuevoUsuarioTipoUser;
    }

    public String getNuevoUsuarioDependencia() {
        return nuevoUsuarioDependencia;
    }

    public void setNuevoUsuarioDependencia(String nuevoUsuarioDependencia) {
        this.nuevoUsuarioDependencia = nuevoUsuarioDependencia;
    }

    public String getActualizarUsuarioCorreo() {
        return actualizarUsuarioCorreo;
    }

    public void setActualizarUsuarioCorreo(String actualizarUsuarioCorreo) {
        this.actualizarUsuarioCorreo = actualizarUsuarioCorreo;
    }

    public Usuario getUsuarioConsultado() {
        return usuarioConsultado;
    }

    public void setUsuarioConsultado(Usuario usuarioConsultado) {
        this.usuarioConsultado = usuarioConsultado;
    }

    public String getChangeTipoUser() {
        return changeTipoUser;
    }

    public void setChangeTipoUser(String changeTipoUser) {
        this.changeTipoUser = changeTipoUser;
    }
}