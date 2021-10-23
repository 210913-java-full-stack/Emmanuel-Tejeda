package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import servlets.InsertNewCustomer;
import servlets.PingServlet;
import java.util.List;


public class Main
{


    public static void main(String[] args)
    {

        Session session = HibernateSetUp.getSession();
        InsertNewCustomer.setSession(session);

        NewCustomer customer1 = new NewCustomer();
        customer1.setCustomerName("Emmanuel");
        customer1.setCustomerLastName("Tejeda");
        customer1.setCustomerID(105);
        customer1.setFlightID(4);


        InsertNewCustomer.saveNewPassenger(customer1);

        HibernateSetUp.closeSession();
    }

}
