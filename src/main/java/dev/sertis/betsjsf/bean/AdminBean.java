package dev.sertis.betsjsf.bean;
import dev.sertis.betsjsf.dao.EventDAOHibernate;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AdminBean {
    public AdminBean() {
        componentPath = "adminUIComponents/adminCerrarEventos.xhtml";
        setLasDosImagenesUnknow();
        eventDAO = EventDAOHibernate.getInstance();
        setTeamImagesAreRendered(false);
    }
    private EventDAOHibernate eventDAO;
    private String componentPath, descripcionEvento, imgLocal, imgVisitante;
    private boolean teamImagesAreRendered;
    private Date fecha;

    public String getComponentPath(){
        return componentPath;
    }

    public void changeComponentToAddQuestionsAndForecasts(){
        setComponentPath("adminUIComponents/adminAnadirPreguntasYPronosticos.xhtml");
    }
    public void changeComponentToCloseEvents(){
        setComponentPath("adminUIComponents/adminCerrarEventos.xhtml");
    }
    public void changeComponentToCreateEvent(){
        setComponentPath("adminUIComponents/adminCrearEvento.xhtml");
    }
    public void changeComponentToEventsList(){
        setComponentPath("commonUIComponents/mostrarEventos.xhtml");
    }

    private void setComponentPath(String path){
        componentPath = path;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: "+event.getObject()));
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public Date getFecha(){
        return fecha;
    }
    public void setDescripcionEvento(String descripcionEvento){
        this.descripcionEvento = descripcionEvento;
    }
    public String getDescripcionEvento(){
        return descripcionEvento;
    }
    public void setImgLocal(String imgLocal){
        this.imgLocal = imgLocal;
    }
    public String getImgLocal(){
        return imgLocal;
    }
    public void setImgVisitante(String imgVisitante){
        this.imgVisitante = imgVisitante;
    }
    public String getImgVisitante(){
        return imgVisitante;
    }
    public boolean getTeamImagesAreRendered(){
        return teamImagesAreRendered;
    }
    public void setTeamImagesAreRendered(boolean teamImagesAreRendered){
        this.teamImagesAreRendered = teamImagesAreRendered;
    }

    public void checkSiSeHanIntroducidoEquiposYActualizarSusImagenes(AjaxBehaviorEvent event){
        System.out.println(String.format("evento: %s", descripcionEvento));

        if(eventoFormatoPartido()){

            imgLocal = getUrlIcono(descripcionEvento.substring(0, descripcionEvento.indexOf("-")).trim().toLowerCase());
            imgVisitante = getUrlIcono(descripcionEvento.split("-")[1].trim().toLowerCase());

            if(!getTeamImagesAreRendered()){
                setTeamImagesAreRendered(true);
            }
        }else{
            setTeamImagesAreRendered(false);
        }
    }

    private boolean eventoFormatoPartido() {
        return descripcionEvento != null && descripcionEvento.contains("-");
    }

    private void setLasDosImagenesUnknow() {
        imgLocal = getUrlIcono("unknown");
        imgVisitante = getUrlIcono("unknown");
    }

    private String getUrlIcono(String nombreEquipo){
        System.out.println(String.format("nombreEquipo: %s", nombreEquipo));
        String urlEquipo = getUrlEquipo(nombreEquipo);
        File icono = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(urlEquipo));
        if(icono.exists()){
            return urlEquipo;
        }
        return getUrlEquipo("unknown");
    }

    private String getUrlEquipo(String equipo){
        return String.format("/resources/icons/laliga/%s.png", equipo);
    }


    public void onAceptarSelected(){
        LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        eventDAO.save(new Event(descripcionEvento, localDate));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento creado correctamente"));
    }








}
