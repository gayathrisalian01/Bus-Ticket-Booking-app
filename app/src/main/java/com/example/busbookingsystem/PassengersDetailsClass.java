package com.example.busbookingsystem;

public class PassengersDetailsClass {

    String time;
    String passengerName;
    String seatNo;
    String travelsName;
    String from;
    String to;
    String duration;
    String type;
    String date;
    int price;

    PassengersDetailsClass(String passengerName,  String seatNo,String travelsName, String from,  String to,String date,String duration,String time, String type,int price)
    {
        this.time=time;
        this.passengerName=passengerName;
        this.seatNo=seatNo;
        this.travelsName=travelsName;
        this.from=from;
        this.to=to;
        this.duration=duration;
        this.type=type;
        this.date=date;
        this.price=price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getTravelsName() {
        return travelsName;
    }

    public void setTravelsName(String travelsName) {
        this.travelsName = travelsName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
