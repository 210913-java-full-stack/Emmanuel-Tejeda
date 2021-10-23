package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity

public class NewCustomer {

    @Id
    private int customerID;
    private int flightID;
    private String customerName;
    private String customerLastName;

    public NewCustomer(){

    }

    public int getCustomerID() {
        return customerID;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

}

