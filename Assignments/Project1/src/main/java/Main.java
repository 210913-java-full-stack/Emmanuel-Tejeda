import org.example.*;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import servlets.InsertAdmin;
import servlets.InsertFlight;
import servlets.InsertNewCustomer;
import servlets.InsertTicket;

import java.util.List;


public class Main
{


    public static void main(String[] args)
    {
        System.out.println("In the main");
//        Session session = HibernateSetUp.getSession();
//
//        InsertAdmin.setSession(session);
//        InsertTicket.setSession(session);
//
//        Ticket newTicket  = new Ticket();
//
//        newTicket.setCustomerID(1);
//        newTicket.setCustomerName("Jenny");
//        newTicket.setCustomerLastName("Jackson");
//        newTicket.setFlightID(1);
//
//
//        Transaction tx = session.beginTransaction();
//
//        InsertTicket.saveNewTicket(newTicket);
//
//        tx.commit();
//        Session session = HibernateSetUp.getSession();
//        InsertFlight.setSession(session);
//
////
//        CreatingFlights thisFlight = new CreatingFlights();
//        List<Flight> allFlights = thisFlight.flightsList();
//
//
//        Transaction tx = session.beginTransaction();
//
//        for(int i = 0; i < 4; i++)
//        {
//            InsertFlight.saveNewFlight(allFlights.get(i));
//        }
//
//        tx.commit();

//        Session session = HibernateSetUp.getSession();
//
//        Transaction tx = session.beginTransaction();
//
//        Query query = session.createQuery("update TicketTable tt set isCheckedIn = 1 where flightID = :flightID and customerID = :customerID");
//        query.setParameter("customerID", new Integer(1));
//        query.setParameter("flightID", new Integer(1));
//
//        query.executeUpdate();
//
//        tx.commit();

    }

}
