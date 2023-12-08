package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.UserDAO;
import dev.sertis.betsjsf.dao.UserDAOImpl;
import dev.sertis.betsjsf.domain.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class SignUpBean {
    private String dni;
    private String name;
    private String lastname;
    private String passwd;
    private String username;
    private boolean isAdmin;
    private final UserDAO userDAO;

    public SignUpBean() {
        userDAO = new UserDAOImpl();
    }

    public String signUp(){
        if (dni==null || dni.isEmpty() || passwd==null || passwd.isEmpty() || name==null || name.isEmpty() || lastname ==null || lastname.isEmpty() || username==null || username.isEmpty()){
            return "error";
        }
        try {
            User user = new User(dni, name, lastname, 0, passwd, username, isAdmin);
            userDAO.save(user);
            return "success";
        } catch (ConstraintViolationException e) {
            FacesContext.getCurrentInstance().addMessage("singup-form",
                    new FacesMessage("Ya existe un usuario con ese DNI"));
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
