package com.hoka.hoka;


public class Password {
    static String login = "";
    static String pass = "";

    public void password(String login, String pass){
        this.login = login;
        this.pass = pass;
    }

    public void setLogin(String login){this.login = login;}
    public String getLogin(){return login;}
    public void setPass(String pass){this.pass = pass;}
    public String getPassword(){return pass;}

    //public static Password selectedItem;

}
