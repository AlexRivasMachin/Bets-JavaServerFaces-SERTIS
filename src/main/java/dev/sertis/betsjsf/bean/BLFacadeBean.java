package dev.sertis.betsjsf.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeLocalImplementation;

public class BLFacadeBean {

    private static BLFacade facade;

    private BLFacadeBean() {
    }

    public static BLFacade getFacade() {
        if (facade == null) {
            facade = new BLFacadeLocalImplementation();
        }
        return facade;
    }
}
