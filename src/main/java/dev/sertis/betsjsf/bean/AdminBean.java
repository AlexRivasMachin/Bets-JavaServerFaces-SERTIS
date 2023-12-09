package dev.sertis.betsjsf.bean;


import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.File;
import java.util.Date;

public class AdminBean {
    public AdminBean() {
        componentPath = "adminUIComponents/adminCerrarEventos.xhtml";
        setLasDosImagenesUnknow();
    }
    private String componentPath, evento, imgLocal, imgVisitante;
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
    public void setEvento(String evento){
        this.evento = evento;
    }
    public String getEvento(){
        return evento;
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
        System.out.println(String.format("evento: %s", evento));

        if(evento != null && evento.contains("-")){
            String pruebas = evento.substring(0, evento.indexOf("-"));
            imgLocal = getUrlIcono(pruebas);
            imgVisitante = getUrlIcono(evento.split("-")[1]);
        }else{
            setLasDosImagenesUnknow();
        }
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







}
