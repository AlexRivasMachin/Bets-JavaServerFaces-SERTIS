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

    Question getQuestionById(Long id);

    void assignResultForecastToQuestion(Long forecastId, Long questionId);

    void deleteUserByDni(String dni);

    User modifyUserBalanceByDni(float balanceModification, String dni);

    Forecast getForecastById(Long forecastId);

    void updateUsersBalanceIfWinners(Long resultantForecastId);

    void deleteBetById(Long id);

    Bet changeBetMoney(float betMoney, Long betId, String dni);

    User changeUserUsername(String dni, String newUsername);

    User changeUserLastName(String dni, String lastName);

    User changeUserName(String dni, String newName);

    User changeUserPassword(String dni, String newPassword);

    User changeUserCreditCard(String dni, Long newCard);
}
