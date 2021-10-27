package servlets;

import org.example.NewCustomer;
import org.example.Ticket;
import org.hibernate.Session;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class InsertTicket {

    private static Session session;

    public static Ticket getTicketById(int id) {
        return session.get(Ticket.class, id);
    }

    public static List<Ticket> getAllTickets() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static void saveNewTicket(Ticket item) {
        //System.out.println("DEBUG - Item saved: " + item.getId() +", " + item.getMessage() + ", " + item.isComplete());
        session.save(item);
    }

    public static void deleteTicket(Ticket item) {
        session.delete(item);
    }


    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        InsertTicket.session = session;
    }
}
