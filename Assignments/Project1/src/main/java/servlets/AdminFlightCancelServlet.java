package servlets;

import org.example.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class AdminFlightCancelServlet extends HttpServlet {



    public Session session = HibernateSetUp.getSession();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("In flight cancel servlet post");
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

            String flightID = (String) jo.get("flightID");

            System.out.println(flightID);
            int flightIdNum = Integer.parseInt(flightID);

            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("delete Flight where flightID = :flightID");
            query.setParameter("flightID", flightIdNum);

            Query query1 = session.createQuery("delete Ticket where flightID = :flightID");
            query1.setParameter("flightID", flightIdNum);

            query.executeUpdate();
            query1.executeUpdate();

            tx.commit();

//            Transaction tx = session.beginTransaction();

//            InsertFlight.saveNewFlight(newFlight);

//            tx.commit();
            //Executing Query

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello From flight cancel get");



    }
}