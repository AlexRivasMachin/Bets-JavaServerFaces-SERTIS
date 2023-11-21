package dev.sertis.betsjsf.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeLocalImplementation;
import domain.User;

public class SignUp {

    private String dni;

    private String name;

    private String lastname;

    private String passwd;

    private String username;

    private boolean isAdmin;

    private final BLFacade facade = new BLFacadeLocalImplementation();

    public SignUp() {
    }

    public String signUp(){
        if (dni==null || dni.isEmpty() || passwd==null || passwd.isEmpty() || name==null || name.isEmpty() || lastname ==null || lastname.isEmpty() || username==null || username.isEmpty()){
            return "error";
        }
        try {
            User user = new User(username, passwd, dni, name, lastname, isAdmin);
            facade.createUser(user);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
