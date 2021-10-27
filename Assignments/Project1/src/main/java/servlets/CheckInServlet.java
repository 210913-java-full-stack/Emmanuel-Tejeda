package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.HibernateSetUp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CheckInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("in check in servlet get");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("In checkIn servlet post");
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

            Query query = session.createQuery("update Ticket set isCheckedIn = 1 where flightID = :flightID and customerID = :customerID");
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
