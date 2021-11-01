package servlets;

import org.example.Flight;
import org.hibernate.Session;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class InsertFlight {

    private static Session session;

    public static Flight getFlightById(int id) {
        return session.get(Flight.class, id);
    }

    public static List<Flight> getAllFlights() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static void saveNewFlight(Flight item) {
        //System.out.println("DEBUG - Item saved: " + item.getId() +", " + item.getMessage() + ", " + item.isComplete());
        session.save(item);
    }

    public static void deleteFlight(Flight item) {
        session.delete(item);
    }


    public static Session getSession(Session session) {
        return InsertFlight.session;
    }

    public static void setSession(Session session) {
        InsertFlight.session = session;
    }

}