package servlets;

import org.example.HibernateSetUp;
import org.example.NewCustomer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CancelFlightServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("cancel flight get servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("In cancel flight servlet post");
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

            System.out.println(customerID);
            System.out.println(flightID);

            //Executing Query
            Session session = HibernateSetUp.getSession();

            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("delete Ticket where flightID = :flightID and customerID = :customerID");
            query.setParameter("customerID", new Integer(customerID));
            query.setParameter("flightID", new Integer(flightID));

            query.executeUpdate();

            tx.commit();


            System.out.println("Ending Json");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
