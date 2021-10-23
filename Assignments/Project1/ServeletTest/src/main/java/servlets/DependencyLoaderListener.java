package servlets;

//import models.NewCustomer; Check with Kyle for this import statement
import org.example.HibernateSetUp;
import org.hibernate.Session;

//import services.HibernateService;
//import services.ToDoItemService;
//import utils.ConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Hibernate config and context initialization
        Session session = HibernateSetUp.getSession();
        InsertNewCustomer.setSession(session);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateSetUp.closeSession();
    }
}
