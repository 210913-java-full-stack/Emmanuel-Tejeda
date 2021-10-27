package org.example;

import javax.persistence.*;

@Entity
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketID;

    private int customerID;
    private int flightID;
    private String customerName;
    private String customerLastName;
    private boolean isCheckedIn = false;

    public Ticket(){}

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getFlightID(){
        return flightID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public boolean getIsCheckedIn() {
        return isCheckedIn;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCheckedIn(boolean isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setFlightID(int flightID){
        this.flightID = flightID;
    }

}
