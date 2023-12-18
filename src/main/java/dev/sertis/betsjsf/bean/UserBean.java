package dev.sertis.betsjsf.bean;
import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.dao.UserDAO;
import dev.sertis.betsjsf.dao.UserDAOHibernate;
import dev.sertis.betsjsf.domain.Bet;
import dev.sertis.betsjsf.domain.User;
import java.util.ArrayList;
public class UserBean {

    private double cantidadRetiro;
    private double cantidadDeposito;
    private String componentContent;
    private ArrayList<Bet> apuestasRealizadas;
    private User loggedUser;
    private final UserDAO userDAO;
    private final BLFacade blFacade;

    public UserBean() {
        this.blFacade = BLFacadeImplementation.getInstance();
        this.userDAO = UserDAOHibernate.getInstance();
        this.loggedUser = LoginBean.getLoggedUser();
        cantidadRetiro = 0.0;
        cantidadDeposito = 0.0;
        apuestasRealizadas = new ArrayList<>();
        //showEventos();
    }
    public String logout() {
        return "logout";
    }


    public String showEventos() {
        componentContent = "commonUIComponents/mostrarEventos.xhtml";
        return null;
    }

    public String showApuestas() {
        componentContent = "userNormalComponentes/apuestasRealizadasComponente.xhtml";
        return null;
    }

    public String showAñadirSaldo() {
        componentContent = "userNormalComponentes/añadirSaldoComponente.xhtml";
        return null;
    }

    public String showDatosPersonales() {
        componentContent = "userNormalComponentes/datosPersonalesComponente.xhtml";
        return null;
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

    }

    public void restarSaldo() {
        if(cantidadRetiro <= loggedUser.getCurrentBalance()) {
            loggedUser.setCurrentBalance(loggedUser.getCurrentBalance() - cantidadRetiro);
        }
    }
    public void setName(String nuevoNombre) {
        if (!nuevoNombre.isEmpty()){
            setLoggedUser(blFacade.changeUserName(loggedUser.getDni(), nuevoNombre));
        }
    }
    public void setApellido(String nuevoApellido) {
        if (!nuevoApellido.isEmpty()){
            setLoggedUser(blFacade.changeUserLastName(loggedUser.getDni(), nuevoApellido));
        }
    }

    public void setTarjetaDeCredito(Long nuevaTarjeta) {
        if(nuevaTarjeta.toString().length()==16){
            setLoggedUser(blFacade.changeUserCreditCard(loggedUser.getDni(), nuevaTarjeta));
        }
    }

    public void setContraseña(String nuevaContraseña) {
        if(!nuevaContraseña.isEmpty()){
            setLoggedUser(blFacade.changeUserPassword(loggedUser.getDni(), nuevaContraseña));
        }
    }

    public void setUsername(String nuevoUsername){
        if(!loggedUser.getUsername().isEmpty()){
            setLoggedUser(blFacade.changeUserUsername(loggedUser.getDni(), nuevoUsername));
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

    /** DATOS APUESTAS **/
    public ArrayList<Bet> getApuestasRealizadas() {
        return apuestasRealizadas;
    }
    public String fecha() {
        return "fecha";
    }
    public String evento() {
        return "evento";
    }
    public String pregunta() {
        return "pregunta";
    }
    public String pronostico() {
        return "pronostico";
    }
    public String ganancia() {
        return "1.0";
    }
    public String apostado() {
        return "10.00";
    }
    public String balance() {
        return "20.00";
    }

    /**COMPONENTES**/
    public String getComponentContent() {
        return componentContent;
    }
    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }
}
