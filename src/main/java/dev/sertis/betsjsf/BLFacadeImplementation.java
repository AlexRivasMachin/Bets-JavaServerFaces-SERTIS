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
    public void saveQuestion(Question question) throws EventFinished, QuestionAlreadyExist {

    }

    @Override
    public List<Event> getEventsByDate(LocalDate localDate) {
        return null;
    }

    @Override
    public List<LocalDate> getDatesWithEventsInAMonth(LocalDate localDate) {
        return null;
    }

    @Override
    public void initializeBD() {

    }

    @Override
    public void saveUser(User user) throws UserAlreadyExist {

    }

    @Override
    public void saveEvent(Event event) throws EventAlreadyExist {

    }

    @Override
    public void saveForecast(Forecast forecast) throws ForecastAlreadyExist, QuestionDoesntExist {

    }

    @Override
    public User getUserByDni(String s) throws UserDoesntExist {
        return null;
    }

    @Override
    public Question getQuestionByQuestionNumber(Integer integer) throws QuestionDoesntExist {
        return null;
    }

    @Override
    public void assignResultForecastToQuestion(Integer integer, Integer integer1) throws QuestionDoesntExist, ForecastDoesntExist, EventHasntFinished {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUserByDni(String s) {

    }

    @Override
    public void saveBet(Bet bet) throws BetAlreadyExist, UserDoesntExist, ForecastDoesntExist {

    }

    @Override
    public User modifyUserBalanceByDni(float v, String s) {
        return null;
    }

    @Override
    public Forecast getForecastByForecastNumber(Integer integer) throws ForecastDoesntExist {
        return null;
    }

    @Override
    public void updateUsersBalanceIfWinners(Integer integer) {

    }

    @Override
    public void deleteBetByBetNumber(Integer integer) throws BetDoesntExist {

    }

    @Override
    public Bet changeBetMoney(float v, int i, String s) throws BetDoesntExist, UserDoesntExist {
        return null;
    }

    @Override
    public User changeUserUsername(User user, String s) {
        return null;
    }

    @Override
    public User changeUserLastName(User user, String s) {
        return null;
    }

    @Override
    public User changeUserName(User user, String s) {
        return null;
    }

    @Override
    public User changeUserPassword(User user, String s) {
        return null;
    }

    @Override
    public User changeUserCreditCard(String s, Long aLong) {
        return null;
    }

    @Override
    public ExtendedIterator<Event> getEventsIterator(Date date) {
        return null;
    }
}
