package dev.sertis.betsjsf.bean;
import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.dao.UserDAO;
import dev.sertis.betsjsf.dao.UserDAOHibernate;
import dev.sertis.betsjsf.domain.Bet;
import dev.sertis.betsjsf.domain.User;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private double cantidadRetiro;
    private double cantidadDeposito;
    private String componentContent;
    private List<Bet> apuestasRealizadas = new ArrayList();
    private User loggedUser;
    private final UserDAO userDAO;
    private final BLFacade blFacade;

    public UserBean() {
        this.blFacade = BLFacadeImplementation.getInstance();
        this.userDAO = UserDAOHibernate.getInstance();
        this.loggedUser = LoginBean.getLoggedUser();
        cantidadRetiro = 0.0;
        cantidadDeposito = 0.0;
        this.apuestasRealizadas = loggedUser.getUserPlacedBets();
        showEventos();
    }
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }


    public void showEventos() {
        componentContent = "commonUIComponents/mostrarEventos.xhtml";
        reloadPage();
    }

    public void showCrearApuesta(){
        componentContent = "userNormalComponentes/crearApuestasComponente.xhtml";
        reloadPage();
    }
    public void showApuestas() {
        componentContent = "userNormalComponentes/apuestasRealizadasComponente.xhtml";
        reloadPage();
    }
    public void showAñadirSaldo() {
        componentContent = "userNormalComponentes/añadirSaldoComponente.xhtml";
        reloadPage();
    }
    public void showDatosPersonales() {
        componentContent = "userNormalComponentes/datosPersonalesComponente.xhtml";
        reloadPage();
    }
    public void reloadPage(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/user.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**DATOS DEL USUARIO**/
    public double getCantidadRetiro() {
        return cantidadRetiro;
    }
    public void setCantidadRetiro(double cantidadRetiro) {
        this.cantidadRetiro = cantidadRetiro;
    }
    public double getCantidadDeposito() {
        return cantidadDeposito;
    }
    public void setCantidadDeposito(double cantidadDeposito) {
        this.cantidadDeposito = cantidadDeposito;
    }

    public void añadirSaldo() {
        loggedUser.setCurrentBalance(loggedUser.getCurrentBalance() + cantidadDeposito);
        reloadPage();
    }
    public void restarSaldo() {
        if(cantidadRetiro <= loggedUser.getCurrentBalance()) {
            loggedUser.setCurrentBalance(loggedUser.getCurrentBalance() - cantidadRetiro);
            reloadPage();
        }
    }
    public void updateUserInfo() {
        blFacade.updateUser(loggedUser);
        setLoggedUser(blFacade.getUserByDni(loggedUser.getDni()));
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    /**COMPONENTES**/
    public String getComponentContent() {
        return componentContent;
    }
    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }
}
