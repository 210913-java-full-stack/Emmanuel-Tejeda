package servlets;

import org.example.NewCustomer;
import org.hibernate.Session;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class InsertNewCustomer {

    private static Session session;

    public static NewCustomer getPassengerById(int id) {
        return session.get(NewCustomer.class, id);
    }

    public static List<NewCustomer> getAllPassengers() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NewCustomer> query = builder.createQuery(NewCustomer.class);
        Root<NewCustomer> root = query.from(NewCustomer.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static void saveNewPassenger(NewCustomer item) {
        //System.out.println("DEBUG - Item saved: " + item.getId() +", " + item.getMessage() + ", " + item.isComplete());
        session.save(item);
    }

    public static void deletePassenger(NewCustomer item) {
        session.delete(item);
    }


    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        InsertNewCustomer.session = session;
    }

}