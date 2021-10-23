package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight
{
    @Id
    private int flightID;
    private String startingCity;
    private String landingCity;

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setStartingCity(String startingCity) {
        this.startingCity = startingCity;
    }

    public void setLandingCity(String landingCity) {
        this.landingCity = landingCity;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public String getLandingCity() {
        return landingCity;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flightID=" + flightID +
                ", startingCity='" + startingCity + '\'' +
                ", landingCity='" + landingCity + '\'' +
                '}';
    }
}
