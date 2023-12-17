package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    private String dni;
    private String name;
    private String lastName;
    private double currentBalance;
    private String passwd;
    private String username;
    private boolean isAdmin;
    private Long creditCard;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Bet> userPlacedBets;

    public User() {
    }

    public User(String dni, String name, String lastName, double currentBalance, String passwd, String username, boolean isAdmin) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.currentBalance = currentBalance;
        this.passwd = passwd;
        this.username = username;
        this.isAdmin = isAdmin;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
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

    public Long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Long creditCard) {
        this.creditCard = creditCard;
    }

    public List<Bet> getUserPlacedBets() {
        return userPlacedBets;
    }

    public void setUserPlacedBets(List<Bet> userPlacedBets) {
        this.userPlacedBets = userPlacedBets;
    }

}