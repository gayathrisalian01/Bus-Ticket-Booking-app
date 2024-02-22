package com.example.busbookingsystem;

public class BusBookingConstructor {
    String _name,_contact,_email,_password;
    int uid;

    public BusBookingConstructor(){

    }

    public BusBookingConstructor(String _name, String _contact, String _email, String _password) {
        this._name = _name;
        this._contact = _contact;
        this._email = _email;
        this._password = _password;
    }
    public BusBookingConstructor(int id,String _name, String _contact, String _email) {
        this.uid=id;
        this._name = _name;
        this._contact = _contact;
        this._email = _email;
    }
    public String get_name(){return _name;}

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_contact(){return _contact;}
    public void set_contact(String _contact) {
        this._contact = _contact;
    }

    public String get_email(){return _email;}


    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password(){return _password;}

    public void set_password(String _password) {
        this._password = _password;
    }

    public int getuid(){return uid;}

    public void set_uid(int uid){
        this.uid=uid;
    }
}
