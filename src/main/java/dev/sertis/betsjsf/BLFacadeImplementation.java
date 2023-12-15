package dev.sertis.betsjsf;

import dev.sertis.betsjsf.dao.*;
import dev.sertis.betsjsf.domain.*;

import java.time.LocalDate;
import java.util.List;

public class BLFacadeImplementation implements BLFacade{

    private final UserDAO userDAO;
    private final EventDAO eventDAO;
    private final QuestionDAO questionDAO;
    private final ForecastDAO forecastDAO;
    private final BetDAO betDAO;
    private static BLFacadeImplementation instance;


    private BLFacadeImplementation() {
        userDAO = UserDAOHibernate.getInstance();
        eventDAO = EventDAOHibernate.getInstance();
        questionDAO = QuestionDAOHibernate.getInstance();
        forecastDAO = ForecastDAOHibernate.getInstance();
        betDAO = BetDAOHibernate.getInstance();
    }

    public static BLFacadeImplementation getInstance() {
        if (instance == null) {
            instance = new BLFacadeImplementation();
        }
        return instance;
    }

    @Override
    public void saveEvent(Event event) {
        eventDAO.save(event);
    }

    @Override
    public void saveQuestion(Question question) {
        questionDAO.save(question);
    }

    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void saveForecast(Forecast forecast) {
        forecastDAO.save(forecast);
    }

    @Override
    public void saveBet(Bet bet) {
        betDAO.save(bet);
    }

    @Override
    public List<Event> getEventsByDate(LocalDate localDate) {
        return eventDAO.getEventsByDate(localDate);
    }

    @Override
    public User getUserByDni(String dni) {
        return userDAO.getUserByDNI(dni);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Question getQuestionById(long id) {
        return questionDAO.getQuestionById(id);
    }

    @Override
    public void assignResultForecastToQuestion(long forecastId, long questionId) {

    }

    @Override
    public void deleteUserByDni(String dni) {
        User userToDelete = userDAO.getUserByDNI(dni);
        userDAO.delete(userToDelete);
    }

    @Override
    public User modifyUserBalanceByDni(double balanceModification, String dni) {
        return null;
    }

    @Override
    public Forecast getForecastById(long forecastId) {
        return forecastDAO.getForecastById(forecastId);
    }

    @Override
    public void updateUsersBalanceIfWinners(long resultantForecastId) {

    }

    @Override
    public void deleteBetById(long id) {
        Bet betToDelete = betDAO.getBetById(id);
        betDAO.delete(betToDelete);
    }

    @Override
    public Bet changeBetMoney(double betMoney, long betId, String dni) {
        Bet bet = betDAO.getBetById(betId);

        final double newAmount = bet.getAmountPlacedOnBet() + betMoney;
        bet.setAmountPlacedOnBet(newAmount);

        betDAO.update(bet);

        this.modifyUserBalanceByDni(- betMoney, dni);
        return bet;
    }

    @Override
    public User changeUserUsername(String dni, String newUsername) {
        User userToModify = userDAO.getUserByDNI(dni);

        userToModify.setUsername(newUsername);
        userDAO.update(userToModify);

        return userToModify;
    }

    @Override
    public User changeUserLastName(String dni, String lastName) {
        User userToModify = userDAO.getUserByDNI(dni);

        userToModify.setLastName(lastName);
        userDAO.update(userToModify);

        return userToModify;
    }

    @Override
    public User changeUserName(String dni, String newName) {
        User userToModify = userDAO.getUserByDNI(dni);

        userToModify.setName(newName);
        userDAO.update(userToModify);

        return userToModify;
    }

    @Override
    public User changeUserPassword(String dni, String newPassword) {
        User userToModify = userDAO.getUserByDNI(dni);

        userToModify.setPasswd(newPassword);
        userDAO.update(userToModify);

        return userToModify;
    }

    @Override
    public User changeUserCreditCard(String dni, long newCard) {
        User userToModify = userDAO.getUserByDNI(dni);

        userToModify.setCreditCard(newCard);
        userDAO.update(userToModify);

        return userToModify;
    }

    @Override
    public boolean isUserPassword(String dni, String password) {
        User user = userDAO.getUserByDNI(dni);
        return user.getPasswd().equals(password);
    }

}