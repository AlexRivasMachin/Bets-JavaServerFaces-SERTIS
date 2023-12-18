package dev.sertis.betsjsf.bean;
import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AdminBean {
    public AdminBean() {
        componentPath = "adminUIComponents/adminCerrarEventos.xhtml";
        BLFacade = BLFacadeImplementation.getInstance();
        setTeamImagesAreRendered(false);
    }
    private BLFacade BLFacade;
    private String componentPath, descripcionEvento, imgLocal, imgVisitante;
    private boolean teamImagesAreRendered;
    private Date fecha;

    public String getComponentPath(){
        return componentPath;
    }

    public void changeComponentToAddQuestionsAndForecasts(){
        setComponentPath("adminUIComponents/adminAnadirPreguntasYPronosticos.xhtml");
        reloadPage();
    }
    public void changeComponentToCloseEvents(){
        setComponentPath("adminUIComponents/adminCerrarEventos.xhtml");
        reloadPage();
    }
    public void changeComponentToCreateEvent(){
        setComponentPath("adminUIComponents/adminCrearEvento.xhtml");
        reloadPage();
    }
    public void changeComponentToEventsList(){
        setComponentPath("commonUIComponents/mostrarEventos.xhtml");
        reloadPage();
    }

    public void reloadPage(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/admin.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setComponentPath(String path){
        componentPath = path;
    }

    public void onDateSelect(SelectEvent event) {
        escribirMensajeDeFechaEscogida(event.getObject().toString());
    }
    private void escribirMensajeDeFechaEscogida(String fecha){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + fecha));
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

    public void onCaracterDeEventoEscrito(AjaxBehaviorEvent event){
        actualizarImagenesDeEquipos();
    }

    private void actualizarImagenesDeEquipos(){
        if(eventoFormatoPartido()){
            acutalizarImagenesEquipos();
            setTeamImagesAreRendered(true);
        }else{
            setTeamImagesAreRendered(false);
        }
    }

    private boolean eventoFormatoPartido() {
        return descripcionEvento != null && descripcionEvento.contains("-");
    }

    private void acutalizarImagenesEquipos(){
        imgLocal = getUrlIcono(descripcionEvento.substring(0, descripcionEvento.indexOf("-")).trim().toLowerCase());
        imgVisitante = getUrlIcono(descripcionEvento.split("-")[1].trim().toLowerCase());
    }

    private String getUrlIcono(String nombreEquipo){
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

    public void onBotonAceptarClicked(){
        guardarEventoEnBD();
        escribirMensajeDeEventoGuardado();
    }
    private void guardarEventoEnBD(){
        LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        BLFacade.saveEvent(new Event(descripcionEvento, localDate));
    }
    private void escribirMensajeDeEventoGuardado(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento guardado correctamente"));
    }


}
