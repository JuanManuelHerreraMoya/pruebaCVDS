package managedbeans;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.Iniciativa;
import entities.Usuario;
import exceptions.ServicesException;
import exceptions.ServiciosUsuarioException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import services.ServiciosIniciativa;
import services.ServiciosUsuario;


@ManagedBean(name = "AdminBean")
@SessionScoped

public class AdminBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{PageBean}")
    private BasePageBean baseBean;
    private ServiciosUsuario serviciosUsuario;
    private ServiciosIniciativa serviciosIniciativa;
    private int idUser;
    private String tipoUser, estado, nombre;
    private Usuario usuario;
    private int checkUpdate;
    private Iniciativa iniciativa;

    public void asignarPerfil() throws ServiciosUsuarioException {
        serviciosUsuario = baseBean.getServiciosUsuario();
        checkUpdate = serviciosUsuario.updateRolUsuario(idUser, tipoUser);

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void modificarIniciativa() throws ServiciosUsuarioException, ServicesException {
        serviciosIniciativa = baseBean.getServiciosIniciativa();
        serviciosIniciativa.updateIniciativa(nombre, estado);
    }

    public Usuario getUsuario() {
        return usuario;
    }
}