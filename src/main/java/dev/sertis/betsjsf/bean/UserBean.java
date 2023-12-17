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
        blFacade = BLFacadeImplementation.getInstance();
        userDAO = UserDAOHibernate.getInstance();
        loggedUser = LoginBean.getLoggedUser();
        System.out.println(loggedUser.getUsername());
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
        printAllUserInfo();

    }
    public double getCantidadDeposito() {
        return cantidadDeposito;
    }
    public void setCantidadDeposito(double cantidadDeposito) {
        this.cantidadDeposito = cantidadDeposito;
        printAllUserInfo();
    }
    public void añadirSaldo() {
        loggedUser.setCurrentBalance(loggedUser.getCurrentBalance() + cantidadDeposito);
        printAllUserInfo();

    }

    public void restarSaldo() {
        if(cantidadRetiro <= loggedUser.getCurrentBalance()) {
            loggedUser.setCurrentBalance(loggedUser.getCurrentBalance() - cantidadRetiro);
            printAllUserInfo();
        }
    }

    public void setDni(String nuevoDni) {
        if(nuevoDni.length() == 9){
            loggedUser.setDni(nuevoDni);
            printAllUserInfo();
        }
    }

    public void setName(String nuevoNombre) {
        if (!nuevoNombre.isEmpty()){
            loggedUser.setName(nuevoNombre);
            printAllUserInfo();
        }
    }
    public void setApellido(String nuevoApellido) {
        if (!nuevoApellido.isEmpty()){
            loggedUser.setLastName(nuevoApellido);
            printAllUserInfo();
        }
    }

    public void setTarjetaDeCredito(Long nuevaTarjeta) {
        if(nuevaTarjeta.toString().length()==16){
            blFacade.changeUserCreditCard(loggedUser.getDni(), nuevaTarjeta);
            printAllUserInfo();
        }
    }

    public void setContraseña(String nuevaContraseña) {
        if(!nuevaContraseña.isEmpty()){
            loggedUser.setPasswd(nuevaContraseña);
            blFacade.changeUserPassword(loggedUser.getDni(), nuevaContraseña);
            printAllUserInfo();
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void printAllUserInfo(){
        System.out.println("DNI: " + loggedUser.getDni());
        System.out.println("Nombre: " + loggedUser.getName());
        System.out.println("Apellido: " + loggedUser.getLastName());
        System.out.println("Saldo: " + loggedUser.getCurrentBalance());
        System.out.println("Contraseña: " + loggedUser.getPasswd());
        System.out.println("Tarjeta de credito: " + loggedUser.getCreditCard());
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
