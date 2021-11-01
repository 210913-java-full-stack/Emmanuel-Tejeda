package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CreatingFlights;
import org.example.Flight;
import org.example.HibernateSetUp;
import org.example.NewCustomer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;



public class CustomerRegistration extends HttpServlet {

    public Session session = HibernateSetUp.getSession();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("in customer registration servlet get");
        try {

            ObjectMapper mapper = new ObjectMapper();
            //Select everything from database
            //query execute
            //place in the data collection

            session.beginTransaction();

            List<Flight> flights = (List<Flight>) session.createQuery("from Flight").list();
            if (flights!=null) {
                for (Flight flight : flights) {
//                    System.out.println(flight.getFlightID() + " - " + flight.getLandingCity() + " - " + flight.getStartingCity() );
                }
            }

            session.getTransaction().commit();


            resp.getWriter().write(mapper.writeValueAsString(flights));
            resp.setContentType("application/json");
            resp.setStatus(200);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        InputStream requestBody = null;
        NewCustomer payload = null;
        try {
            requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            System.out.println("DEBUG - JSON TEXT: " + jsonText);
            ObjectMapper mapper = new ObjectMapper();
            payload = mapper.readValue(jsonText, NewCustomer.class);
            InsertNewCustomer.saveNewPassenger(payload);

            resp.setStatus(202);
            resp.getWriter().write(mapper.writeValueAsString(payload));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}