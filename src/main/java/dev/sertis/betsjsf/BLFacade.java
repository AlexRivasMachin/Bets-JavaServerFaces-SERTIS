package dev.sertis.betsjsf;

import dev.sertis.betsjsf.domain.*;

import java.time.LocalDate;
import java.util.List;

public interface BLFacade {

    void saveEvent(Event event);

    void saveQuestion(Question question);

    void saveUser(User user);

    void saveForecast(Forecast forecast);

    void saveBet(Bet bet);

    List<Event> getEventsByDate(LocalDate localDate);

    User getUserByDni(String dni);

    List<User> getAllUsers();

    Question getQuestionById(long id);

    void assignResultForecastToQuestion(long forecastId, long questionId);

    void deleteUserByDni(String dni);

    User modifyUserBalanceByDni(double balanceModification, String dni);

    Forecast getForecastById(long forecastId);

    void updateUsersBalanceIfWinners(long resultantForecastId);

    void deleteBetById(long id);

    Bet changeBetMoney(double betMoney, long betId, String dni);

    User changeUserUsername(String dni, String newUsername);

    User changeUserLastName(String dni, String lastName);

    User changeUserName(String dni, String newName);

    User changeUserPassword(String dni, String newPassword);

    User changeUserCreditCard(String dni, long newCard);

    boolean isUserPassword(String dni, String password);
}
