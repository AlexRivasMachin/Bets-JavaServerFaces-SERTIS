package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateEventBean {
    public CreateEventBean() {
        BLFacade = BLFacadeImplementation.getInstance();
        setTeamImagesAreRendered(false);
    }

    private BLFacade BLFacade;
    private String  descripcionEvento, imgLocal, imgVisitante;
    private boolean teamImagesAreRendered;
    private Date fecha;

    public void onDateSelect(SelectEvent event) {
        escribirMensajeDeFechaEscogida(event.getObject().toString());
        eliminarMensajeDeEventoGuardad();
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
        eliminarMensajeDeEventoGuardad();
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
    private void eliminarMensajeDeEventoGuardad(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(""));
    }

    public void pruebaPrint(){
        System.out.println("prueba");
    }
}
