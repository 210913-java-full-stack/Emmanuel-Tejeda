package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Flight;
import org.example.HibernateSetUp;
import org.example.NewCustomer;
import org.example.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class TicketKioskServlet extends HttpServlet {
    public Session session = HibernateSetUp.getSession();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("In Ticket Kiosk Get");
        try {
            ObjectMapper mapper = new ObjectMapper();
            //Select everything from database
            //query execute
            //place in the data collection

            session.beginTransaction();

            List<Ticket> tickets = (List<Ticket>) session.createQuery("from Ticket").list();
            if (tickets != null) {
                for (Ticket ticket : tickets) {
                    System.out.println(ticket.getFlightID() + " - " + ticket.getCustomerName());
                }
            }

            session.getTransaction().commit();


            resp.getWriter().write(mapper.writeValueAsString(tickets));
            resp.setContentType("application/json");
            resp.setStatus(200);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("In the Ticket Kiosk Servlet");
        InputStream requestBody = null;
        try {
            requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            resp.setStatus(202);

            System.out.println("DEBUG - JSON TEXT: " + jsonText);

            //Setting the json object
            Object obj = new JSONParser().parse(jsonText);
            JSONObject jo = (JSONObject) obj;

            String customerID = (String) jo.get("customerID");
            String flightID = (String) jo.get("flightID");

            int customerIdNum = Integer.parseInt(customerID);
            int flightIdNum = Integer.parseInt(flightID);

            System.out.println(customerIdNum);
            System.out.println(flightIdNum);

            //Retrieving the customer
            InsertNewCustomer.setSession(session);

            NewCustomer customer = session.get(NewCustomer.class, customerIdNum);
            Flight flight = session.get(Flight.class, flightIdNum);

            Ticket newTicket  = new Ticket();


            newTicket.setCustomerID(customer.getCustomerID());
            newTicket.setCustomerName(customer.getCustomerName());
            newTicket.setCustomerLastName(customer.getCustomerLastName());
            newTicket.setFlightID(flight.getFlightID());

            System.out.println(customer.getCustomerID());
            System.out.println(customer.getCustomerName());

            Transaction tx = session.beginTransaction();

            InsertTicket.saveNewTicket(newTicket);

            tx.commit();




//           SessionFactory sf = new Configuration().configure().build

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}