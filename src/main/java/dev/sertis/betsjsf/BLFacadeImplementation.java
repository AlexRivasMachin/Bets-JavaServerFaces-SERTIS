package dev.sertis.betsjsf;

import domain.*;
import exceptions.*;
import iterators.ExtendedIterator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import businessLogic.BLFacade;
public class BLFacadeImplementation implements BLFacade{
    @Override
    public Question saveQuestion(Question question) throws EventFinished, QuestionAlreadyExist {
        return null;
    }

    @Override
    public List<Event> getEventsByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<LocalDate> getDatesWithEventsInAMonth(LocalDate date) {
        return null;
    }

    @Override
    public void initializeBD() {

    }

    @Override
    public User saveUser(User user) throws UserAlreadyExist {
        return null;
    }

    @Override
    public Event saveEvent(Event event) throws EventAlreadyExist {
        return null;
    }

    @Override
    public Forecast saveForecast(Forecast forecast) throws ForecastAlreadyExist, QuestionDoesntExist {
        return null;
    }

    @Override
    public User getUserByDni(String dni) throws UserDoesntExist {
        return null;
    }

    @Override
    public Question getQuestionByQuestionNumber(Integer questionNumber) throws QuestionDoesntExist {
        return null;
    }

    @Override
    public void assignResultForecastToQuestion(Integer questionNumber, Integer forecastNumber) throws QuestionDoesntExist, ForecastDoesntExist, EventHasntFinished {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean deleteUserByDni(String dni) {
        return false;
    }

    @Override
    public Bet saveBet(Bet bet) throws BetAlreadyExist, UserDoesntExist, ForecastDoesntExist {
        return null;
    }

    @Override
    public User modifyUserBalanceByDni(float balanceModification, String dni) {
        return null;
    }

    @Override
    public Forecast getForecastByForecastNumber(Integer forecastNumber) throws ForecastDoesntExist {
        return null;
    }

    @Override
    public void updateUsersBalanceIfWinners(Integer resultantForecastNumber) {

    }

    @Override
    public void deleteBetByBetNumber(Integer betNumber) throws BetDoesntExist {

    }

    @Override
    public Bet changeBetMoney(float betMoney, int betNumber, String dni) throws BetDoesntExist, UserDoesntExist {
        return null;
    }

    @Override
    public void changeUserUsername(User user, String newUsername) {

    }

    @Override
    public void changeUserLastName(User user, String lastName) {

    }

    @Override
    public void changeUserName(User user, String newName) {

    }

    @Override
    public void changeUserPassword(User user, String newPassword) {

    }

    @Override
    public void changeUserCreditCard(String user, Long newCard) {

    }

    @Override
    public ExtendedIterator<Event> getEventsIterator(Date date) {
        return null;
    }
}
