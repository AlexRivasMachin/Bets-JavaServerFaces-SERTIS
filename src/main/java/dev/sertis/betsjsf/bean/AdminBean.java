package dev.sertis.betsjsf.bean;
import dev.sertis.betsjsf.dao.EventDAO;
import dev.sertis.betsjsf.dao.EventDAOImpl;
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
        eventDAO = new EventDAOImpl();
    }
    private EventDAO eventDAO;
    private String componentPath, descripcionEvento, imgLocal, imgVisitante;
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

    public void checkSiSeHanIntroducidoEquiposYActualizarSusImagenes(AjaxBehaviorEvent event){
        System.out.println(String.format("evento: %s", descripcionEvento));

        if(eventoFormatoCorrecto()){
            String pruebas = descripcionEvento.substring(0, descripcionEvento.indexOf("-"));
            imgLocal = getUrlIcono(pruebas);
            imgVisitante = getUrlIcono(descripcionEvento.split("-")[1]);
        }else{
            setLasDosImagenesUnknow();
        }
    }

    private boolean eventoFormatoCorrecto() {
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
        if(eventoFormatoCorrecto()){
            LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            eventDAO.save(new Event(descripcionEvento, localDate));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento creado correctamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Introduzca un evento válido"));
        }
    }








}
