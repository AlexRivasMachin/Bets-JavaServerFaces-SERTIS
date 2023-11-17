package dev.sertis.betsjsf.bean;

import java.io.Serializable;


public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public HelloBean() {
        this.name = "World";
    }

    public HelloBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
