package com.example.busbookingsystem;

import java.io.Serializable;
import java.util.Objects;

public class busdetails implements Serializable
{
    int busId;
    String Time;
    String TravelName;
    String Price;
    String Duration;
    String Type;
    public int getBusId() {
        return busId;
    }

    public void setBusId(int id) {
        busId = id;
    }
    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTravelName() {
        return TravelName;
    }

    public void setTravelName(String travelName) {
        TravelName = travelName;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        busdetails that = (busdetails) o;
        return Objects.equals(Time, that.Time) &&
                Objects.equals(TravelName, that.TravelName) &&
                Objects.equals(Price, that.Price) &&
                Objects.equals(Duration, that.Duration) &&
                Objects.equals(Type, that.Type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Time, TravelName, Price, Duration, Type);
    }



}
