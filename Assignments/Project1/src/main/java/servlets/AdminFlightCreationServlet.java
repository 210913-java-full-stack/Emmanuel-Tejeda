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


public class AdminFlightCreationServlet extends HttpServlet {



    public Session session = HibernateSetUp.getSession();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("In flight creation servlet post");
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

            String arrivalCity = (String) jo.get("arrivalCity");
            String departureCity = (String) jo.get("departureCity");

            System.out.println(arrivalCity);
            System.out.println(departureCity);

            InsertFlight.getSession(session);

            Flight newFlight = new Flight();

            newFlight.setStartingCity(departureCity);
            newFlight.setLandingCity(arrivalCity);

            Transaction tx = session.beginTransaction();

            InsertFlight.saveNewFlight(newFlight);

            tx.commit();
            //Executing Query

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello From flight creation get");



    }
}