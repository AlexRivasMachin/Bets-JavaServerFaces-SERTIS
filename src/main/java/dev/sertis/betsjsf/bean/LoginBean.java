package dev.sertis.betsjsf.bean;

import businessLogic.BLFacade;
import domain.User;
import exceptions.UserDoesntExist;

public class LoginBean {

    private String dni;

    private String passwd;

    private final BLFacade facade;

    public LoginBean() {
        this.facade = BLFacadeBean.getFacade();
    }

    public String checkCredentials(){
        if (dni==null || dni.isEmpty() || passwd==null || passwd.isEmpty()){
            return "error";
        }
        try {
            User user = facade.getUser(dni);
            if (user.checkCredentials(passwd)){
                if (user.isAdmin()){
                    return "admin";
                } else {
                    return "user";
                }
            } else {
                return "error";
            }
        } catch (UserDoesntExist e) {
            return "error";
        }
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
}