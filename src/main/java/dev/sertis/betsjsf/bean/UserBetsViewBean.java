package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Bet;
import dev.sertis.betsjsf.domain.User;

import java.io.Serializable;
import java.util.List;

public class UserBetsViewBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final BLFacade blFacade;
    private List<Bet> betsFromLoggedUser;

    public UserBetsViewBean() {
        this.blFacade = BLFacadeImplementation.getInstance();
        final String dni = LoginBean.getLoggedUser().getDni();
        this.betsFromLoggedUser = blFacade.getBetsByUserDni(dni);
    }


    public List<Bet> getBetsFromLoggedUser() {
        return this.betsFromLoggedUser;
    }

    public void setBetsFromLoggedUser(List<Bet> betsFromLoggedUser) {
        this.betsFromLoggedUser = betsFromLoggedUser;
    }
}
