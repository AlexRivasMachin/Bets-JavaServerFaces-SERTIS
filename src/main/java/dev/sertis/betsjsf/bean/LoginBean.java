package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginBean {
    private String dni;
    private String passwd;
    private static User loggedUser;
    private final BLFacade blFacade;

    public LoginBean() {
        this.blFacade = BLFacadeImplementation.getInstance();
    }

    public String processLogin(){
        if (dni==null || dni.isEmpty() || passwd==null || passwd.isEmpty()){
            return "error";
        }
        try {
            User user = blFacade.getUserByDni(dni);
            if (blFacade.isUserPassword(user.getDni(), passwd)){
                setLoggedUser(user);
                if (user.isAdmin()){
                    return "admin";
                } else {
                    return "user";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("login-form",
                        new FacesMessage("Contraseña incorrecta"));
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    private boolean isPasswordCorrect(User user, String passwd){
        return user.getPasswd().equals(passwd);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public static User getLoggedUser(){
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        LoginBean.loggedUser = loggedUser;
    }

    public static boolean isUserLogged(){
        return loggedUser != null;
    }
}
