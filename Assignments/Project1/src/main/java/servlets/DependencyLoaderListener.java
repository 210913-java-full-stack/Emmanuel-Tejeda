package servlets;

//import models.NewCustomer; Check with Kyle for this import statement
import org.example.HibernateSetUp;
import org.hibernate.Session;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Hibernate config and context initialization
        Session session = HibernateSetUp.getSession();
        InsertNewCustomer.setSession(session);
        InsertFlight.setSession(session);
        InsertTicket.setSession(session);
        InsertAdmin.setSession(session);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateSetUp.closeSession();
    }

}
