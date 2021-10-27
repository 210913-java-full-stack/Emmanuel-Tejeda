package servlets;

import org.example.Administrators;
import org.hibernate.Session;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class InsertAdmin {

    private static Session session;

    public static Administrators getAdminById(int id) {
        return session.get(Administrators.class, id);
    }

    public static List<Administrators> getAllAdmins() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Administrators> query = builder.createQuery(Administrators.class);
        Root<Administrators> root = query.from(Administrators.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static void saveNewAdmin(Administrators item) {
        //System.out.println("DEBUG - Item saved: " + item.getId() +", " + item.getMessage() + ", " + item.isComplete());
        session.save(item);
    }

    public static void delete(Administrators item) {
        session.delete(item);
    }


    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        InsertAdmin.session = session;
    }

}