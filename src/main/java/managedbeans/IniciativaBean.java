package managedbeans;


import entities.Comentario;
import entities.Iniciativa;
import entities.Usuario;
import entities.Voto;
import exceptions.ServicesException;
import exceptions.ServiciosUsuarioException;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import services.ServiciosComentario;
import services.ServiciosIniciativa;
import services.ServiciosUsuario;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.BarChartModel;
import services.ServiciosVoto;
import managedbeans.ChartBean;

@ManagedBean(name = "IniciativaBean")
@SessionScoped

public class IniciativaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{PageBean}")
    private BasePageBean baseBean;
    private ServiciosUsuario serviciosUsuario;
    private ServiciosIniciativa serviciosIniciativa;
    private ServiciosVoto serviciosVoto;
    private ServiciosComentario serviciosComentario;
    //private Iniciativa iniciativa;
    private int checkUpdate;
    private String nombreIniciativa;
    private String descripcionIniciativa;
    private String palabrasClave;
    private String estado;
    private String nombreUsuario;
    private String correoUsuario;
    private Usuario usuario;
    private Iniciativa iniciativaConsultadaId;
    private Iniciativa iniciativaConsultadaId1;
    private List<Integer> estadistica;
	private BarChartModel model;
	private List<Comentario> comentarios;
    private List<Comentario> comentarios1;
    private String comentario;
    private String comentario2;
    private String estadoParaFiltrar;
    private int idRelacionar;
    private List<Iniciativa> iniciativaEst = null;
    private ChartBean chartView;

    public void Bean() {
        model = new BarChartModel();
        ChartSeries e= new ChartSeries();
        e.setLabel("Estadisticas");
        e.set("Finanzas", estadistica.get(0));
		e.set("Administrativo", estadistica.get(1));
		e.set("Recursos humanos", estadistica.get(2));
		e.set("TI", estadistica.get(3));
		e.set("Unidad de proyectos", estadistica.get(4));
		//e.set("En espera de revisión", estadisticaArea.get(5));
        //e.set("En revisión", estadisticaArea.get(6));
        //e.set("Proyecto", estadisticaArea.get(7));
        //e.set("Solucionado", estadisticaArea.get(8));
		model.addSeries(e);
		//model.addSeries(o);
		model.setTitle("Estadísticas");
        model.setLegendPosition("ne");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Dependencias y Areas");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Iniciativas registradas");
        yAxis.setMin(0);
        yAxis.setMax(15);
	}

	public BarChartModel getModel() {
        return model;
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }

    public List<Iniciativa> getIniciativas(){
        configBasica();
        List<Iniciativa> iniciativas = null;
        try {
            iniciativas = serviciosIniciativa.getIniciativas();
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
        return iniciativas;
    }

    public List<Iniciativa> getMisIniciativas(){
        List<Iniciativa> iniciativas1 = null;
        try {
            iniciativas1 = serviciosIniciativa.getIniciativaProponente(usuario.getNombre());
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
        return iniciativas1;
    }

    public void getIniciativasEst(){
        try {
            iniciativaEst = serviciosIniciativa.getIniciativasEst(estadoParaFiltrar);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/filtrarEstado.xhtml");
        } catch (Exception e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void actualizarIniciativa() {
        try {
            serviciosIniciativa.updateIniciativa(nombreIniciativa, estado);
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void relacionarIniciativas(int idIniciativa){
        try {
            iniciativaConsultadaId = serviciosIniciativa.getIniciativaId(idIniciativa);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/relacionarIniciativa.xhtml");
        } catch (ServicesException | IOException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void relacionar(){
        try {
            serviciosIniciativa.updateIniciativaRelacionada(iniciativaConsultadaId.getId(), idRelacionar);
            verIniciativa(iniciativaConsultadaId.getId());
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void verIniciativa(int idIniciativa){
        try {
            iniciativaConsultadaId = serviciosIniciativa.getIniciativaId(idIniciativa);
            comentarios = serviciosComentario.getComentarios(iniciativaConsultadaId.getId());
            if(usuario.getTipoUser().equals("PMO")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/ModificarIniciativa.xhtml");
            } else if(iniciativaConsultadaId.getIdIniciativaRelacionada()!=-1){
                iniciativaConsultadaId1 = serviciosIniciativa.getIniciativaId(iniciativaConsultadaId.getIdIniciativaRelacionada());
                comentarios1 = serviciosComentario.getComentarios(iniciativaConsultadaId1.getId());
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/verIniciativas.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/verIniciativa.xhtml");
            }
        } catch (IOException | ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void modificar(int idIniciativa){
        try {
            modificarIniciativa(idIniciativa);
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void modificarIniciativa(int idIniciativa) throws ServicesException{
        try {
            iniciativaConsultadaId = serviciosIniciativa.getIniciativaId(idIniciativa);
            if(iniciativaConsultadaId.getEstado().equals("En espera de revisión")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/actualizarIniciativa.xhtml");
            } else {
                throw new ServicesException("No se puede actualizar esta iniciativa, ya ha cambiado de estado");
            }
        } catch (IOException | ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void guardarComentario(String comment, Iniciativa iniciativa){
        try {
            Comentario comentario1 = new Comentario(comment, iniciativa.getId(), usuario.getId());
            serviciosComentario.insertComentario(comentario1);
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void comentarIniciativa1() throws ServicesException {
        if(comentario !=null && !comentario.equals("")) {
            guardarComentario(comentario, iniciativaConsultadaId);
            comentarios = serviciosComentario.getComentarios(iniciativaConsultadaId.getId());
            comentario=null;
        }else {
            throw new ServicesException("No se puede comentar esta iniciativa");
        }
    }

    public void comentarIniciativa2() throws ServicesException {
        if(comentario2 !=null && !comentario2.equals("")) {
            guardarComentario(comentario2, iniciativaConsultadaId1);
            comentarios1 = serviciosComentario.getComentarios(iniciativaConsultadaId1.getId());
            comentario2=null;
        }else {
            throw new ServicesException("No se puede comentar esta iniciativa");
        }
    }

    public void iniciativaPorArea(){
        estadistica = new ArrayList<Integer>();

        try{
            int finanzas =0;
            int administrativo=0;
            int recursosHumanos=0;
            int TI=0;
            int unidadDeProyectos = 0;
            int EnEsperaRevision =0;
            int EnRevision=0;
            int proyecto=0;
            int Solucionado=0;
            List<Iniciativa> iniciativas = serviciosIniciativa.getIniciativas();
            List<Usuario> usuarios = serviciosUsuario.consultarUsuarios();
            for(int i=0; i<iniciativas.size(); i++){
                for(int j=0; j<usuarios.size(); j++){
                    if(iniciativas.get(i).getCorreoUsuario().equals(usuarios.get(j).getCorreo())){
                        if(usuarios.get(j).getDependencia().equals("Finanzas")){ finanzas+=1; }
                        else if(usuarios.get(j).getDependencia().equals("Administrativo")){ administrativo+=1; }
                        else if(usuarios.get(j).getDependencia().equals("Recursos Humanos")){ recursosHumanos+=1; }
                        else if(usuarios.get(j).getDependencia().equals("TI")){ TI+=1; }
                        else if(usuarios.get(j).getDependencia().equals("Unidad de proyectos")){ unidadDeProyectos+=1; }
                    }
                }
            }

            for(int i=0; i<iniciativas.size(); i++) {
                if (iniciativas.get(i).getEstado().equals("En espera de revisión")) {
                    EnEsperaRevision += 1;
                } else if (iniciativas.get(i).getEstado().equals("En revisión")) {
                    EnRevision += 1;
                } else if (iniciativas.get(i).getEstado().equals("Proyecto")) {
                    proyecto += 1;
                } else if (iniciativas.get(i).getEstado().equals("Solucionado")) {
                    Solucionado += 1;
                }
            }
            estadistica.add(finanzas);
            estadistica.add(administrativo);
            estadistica.add(recursosHumanos);
            estadistica.add(TI);
            estadistica.add(unidadDeProyectos);
            estadistica.add(EnEsperaRevision);
            estadistica.add(EnRevision);
            estadistica.add(proyecto);
            estadistica.add(Solucionado);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/informes.html");
        //ExportarCSV(iniciativas);
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        } catch (ServiciosUsuarioException e) {
            this.baseBean.mensajeApp(e);
        } catch (IOException e) {
            this.baseBean.mensajeApp(e);
        }
    }
/*
    public static void ExportarCSV(List<Iniciativa> inicitivaInfoCVS) {
        String salidaArchivo = "inicitivaInfoCVS.csv"; // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe

        // Si existe un archivo llamado asi lo borra
        if(existe) {
            File archivoinicitivaInfoCVS = new File(salidaArchivo);
            archivoinicitivaInfoCVS.delete();
        }

        try {
            // Crea el archivo
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true), ',');

            // Datos para identificar las columnas
            salidaCSV.write("Nombre");
            salidaCSV.write("Estado");
            salidaCSV.write("Numero Votos");
            salidaCSV.write("Descripcion");

            salidaCSV.endRecord(); // Deja de escribir en el archivo

            // Recorremos la lista y lo insertamos en el archivo
            for(Iniciativa ini : inicitivaInfoCVS) {
                salidaCSV.write(ini.getNombre());
                salidaCSV.write(ini.getEstado());
                salidaCSV.write(String.valueOf(ini.getNumeroVotos()));
                salidaCSV.write(ini.getDescripcion());
                //int numEntero = 4;
                //String numCadena= String.valueOf(numEntero);
                salidaCSV.endRecord(); // Deja de escribir en el archivo
            }

            salidaCSV.close(); // Cierra el archivo

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void ImportarCSV() {
        try{
            List<Usuario> usuarios = new ArrayList<Usuario>(); // Lista donde guardaremos los datos del archivo

            CsvReader leerUsuarios = new CsvReader("Usuarios.csv");
            leerUsuarios.readHeaders();

            // Mientras haya lineas obtenemos los datos del archivo
            while(leerUsuarios.readRecord()) {
                String nombre = leerUsuarios.get(0);
                String telefono = leerUsuarios.get(1);
                String email = leerUsuarios.get(2);

                usuarios.add(new Usuario(nombre, telefono, email)); // Añade la informacion a la lista
            }

            leerUsuarios.close(); // Cierra el archivo

            // Recorremos la lista y la mostramos en la pantalla
            for(Usuario user : usuarios) {
                System.out.println(user.getNombre() + " , "
                        + user.getTelefono() + " , "
                        +user.getEmail());
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

     */

    public void configBasica() {
        setServiciosIniciativa(baseBean.getServiciosIniciativa());
        setServiciosUsuario(baseBean.getServiciosUsuario());
        setServiciosVoto(baseBean.getServiciosVoto());
        setServiciosComentario(baseBean.getServiciosComentario());
        setUsuario(baseBean.getUser());
        setNombreUsuario(usuario.getNombre());
        setCorreoUsuario(usuario.getCorreo());
    }

    public void borrarForm() {
        setDescripcionIniciativa("");
        setNombreIniciativa("");
        setPalabrasClave("");
        setPalabrasClave("");
        setEstado("");
    }

    public void agregarIniciativa() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/Pro.xhtml");
            checkUpdate = serviciosIniciativa.insertIniciativa(new Iniciativa(nombreIniciativa, "En espera de revisión", 0, descripcionIniciativa, palabrasClave, nombreUsuario, correoUsuario));
            borrarForm();
        } catch (IOException | ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void votar(){
        try {
            if(iniciativaConsultadaId.getIdIniciativaRelacionada()!=-1){
                Voto voto1 = new Voto(usuario.getId(), iniciativaConsultadaId1.getId());
                serviciosVoto.insertVoto(voto1);
                serviciosIniciativa.updateVotosIniciativa(iniciativaConsultadaId1.getNombre(), iniciativaConsultadaId1.getNumeroVotos()+1);
                iniciativaConsultadaId1 = serviciosIniciativa.getIniciativaId(iniciativaConsultadaId1.getId());
            }
            Voto voto = new Voto(usuario.getId(), iniciativaConsultadaId.getId());
            //serviciosVoto.getVoto(usuario.getId(), iniciativaConsultadaId.getId());
            serviciosVoto.insertVoto(voto);
            serviciosIniciativa.updateVotosIniciativa(iniciativaConsultadaId.getNombre(), iniciativaConsultadaId.getNumeroVotos()+1);
            iniciativaConsultadaId = serviciosIniciativa.getIniciativaId(iniciativaConsultadaId.getId());
        }catch (Exception e){
            if(e.getMessage().equals("Error al insertar voto")){
                try {
                    if(iniciativaConsultadaId.getIdIniciativaRelacionada()!=-1){
                        serviciosVoto.deleteVoto(usuario.getId(), iniciativaConsultadaId1.getId());
                        serviciosIniciativa.updateVotosIniciativa(iniciativaConsultadaId1.getNombre(), iniciativaConsultadaId1.getNumeroVotos()-1);
                        iniciativaConsultadaId1 = serviciosIniciativa.getIniciativaId(iniciativaConsultadaId1.getId());
                    }
                    serviciosVoto.deleteVoto(usuario.getId(), iniciativaConsultadaId.getId());
                    serviciosIniciativa.updateVotosIniciativa(iniciativaConsultadaId.getNombre(), iniciativaConsultadaId.getNumeroVotos()-1);
                    iniciativaConsultadaId = serviciosIniciativa.getIniciativaId(iniciativaConsultadaId.getId());
                } catch (ServicesException ex) {
                    this.baseBean.mensajeApp(e);
                }
            }else {
                this.baseBean.mensajeApp(e);
            }
        }
    }


    private void cambiarEstado(String nuevoEstado){
        try {
            serviciosIniciativa.updateIniciativa(iniciativaConsultadaId.getNombre(), estado);
            iniciativaConsultadaId = serviciosIniciativa.getIniciativaId(iniciativaConsultadaId.getId());
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public void cambiarEstado() throws ServicesException {
        if(estado !=null && !estado.equals("")) {
            cambiarEstado(estado);
        }else {
            throw new ServicesException("Fallo al actualizar el estado de la iniciativa");
        }
    }

    public void actualizarLaIniciativa(){
        try {
            serviciosIniciativa.updateIniciativaDesc(iniciativaConsultadaId.getId(), iniciativaConsultadaId.getDescripcion());
            serviciosIniciativa.updateIniciativaPalabrasC(iniciativaConsultadaId.getId(), iniciativaConsultadaId.getPalabrasClave());
            serviciosIniciativa.updateIniciativaNombre(iniciativaConsultadaId.getId(), iniciativaConsultadaId.getNombre());
        } catch (ServicesException e) {
            this.baseBean.mensajeApp(e);
        }
    }

    public BasePageBean getBaseBean() {
        return baseBean;
    }

    public void setBaseBean(BasePageBean baseBean) {
        this.baseBean = baseBean;
    }

    public ServiciosUsuario getServiciosUsuario() {
        return serviciosUsuario;
    }

    public void setServiciosUsuario(ServiciosUsuario serviciosUsuario) {
        this.serviciosUsuario = serviciosUsuario;
    }

    public ServiciosIniciativa getServiciosIniciativa() {
        return serviciosIniciativa;
    }

    public void setServiciosIniciativa(ServiciosIniciativa serviciosIniciativa) {
        this.serviciosIniciativa = serviciosIniciativa;
    }

    public String getNombreIniciativa() {
        return nombreIniciativa;
    }

    public void setNombreIniciativa(String nombreIniciativa) {
        this.nombreIniciativa = nombreIniciativa;
    }

    public String getDescripcionIniciativa() {
        return descripcionIniciativa;
    }

    public void setDescripcionIniciativa(String descripcionIniciativa) {
        this.descripcionIniciativa = descripcionIniciativa;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Integer> getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(List<Integer> estadistica) {
        this.estadistica = estadistica;
    }

    public List<Iniciativa> getIniciativaEst() {
        return iniciativaEst;
    }

    public void setIniciativaEst(List<Iniciativa> iniciativaEst) {
        this.iniciativaEst = iniciativaEst;
    }

    public Iniciativa getIniciativaConsultadaId() {
        return iniciativaConsultadaId;
    }

    public void setIniciativaConsultadaId(Iniciativa iniciativaConsultadaId) {
        this.iniciativaConsultadaId = iniciativaConsultadaId;
    }

    public Iniciativa getIniciativaConsultadaId1() {
        return iniciativaConsultadaId1;
    }

    public void setIniciativaConsultadaId1(Iniciativa iniciativaConsultadaId1) {
        this.iniciativaConsultadaId1 = iniciativaConsultadaId1;
    }

    public ServiciosVoto getServiciosVoto() {
        return serviciosVoto;
    }

    public void setServiciosVoto(ServiciosVoto serviciosVoto) {
        this.serviciosVoto = serviciosVoto;
    }

    public ServiciosComentario getServiciosComentario() {
        return serviciosComentario;
    }

    public void setServiciosComentario(ServiciosComentario serviciosComentario) {
        this.serviciosComentario = serviciosComentario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentarios1() {
        return comentarios1;
    }

    public void setComentarios1(List<Comentario> comentarios1) {
        this.comentarios1 = comentarios1;
    }

    public int getIdRelacionar() {
        return idRelacionar;
    }

    public void setIdRelacionar(int idRelacionar) {
        this.idRelacionar = idRelacionar;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario2() {
        return comentario2;
    }

    public void setComentario2(String comentario2) {
        this.comentario2 = comentario2;
    }

    public String getEstadoParaFiltrar() {
        return estadoParaFiltrar;
    }

    public void setEstadoParaFiltrar(String estadoParaFiltrar) {
        this.estadoParaFiltrar = estadoParaFiltrar;
    }


}