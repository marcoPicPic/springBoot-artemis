package com.codenotfound.domain;

import java.io.Serializable;


public class User implements Serializable {

    private  static  final  long serialVersionUID =  1350092881346723535L;
    private String name;
    private String firstName;

    public User() {
    }

    public User(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }
}
