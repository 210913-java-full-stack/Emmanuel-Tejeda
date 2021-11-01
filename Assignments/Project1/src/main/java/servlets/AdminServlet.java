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


public class AdminServlet extends HttpServlet {



    public Session session = HibernateSetUp.getSession();
    boolean adminCheck = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

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

            String userName = (String) jo.get("userName");
            String password = (String) jo.get("password");
            String adminID = (String) jo.get("adminID");

            int adminIdNum = Integer.parseInt(adminID);


            //Executing Query

            Administrators newAdmin = session.get(Administrators.class, adminIdNum);

            if(userName.equalsIgnoreCase(newAdmin.getUserName()) && password.equalsIgnoreCase(newAdmin.getPassword()))
            {
                adminCheck = true;
                System.out.println("Everything checks out");
            }else{
                adminCheck = false;
                System.out.println("Incorrect Username or password");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("In admin get servlet");
        System.out.println("Admin check = " +adminCheck);

        resp.getWriter().write("Hello From admin");
        resp.setContentType("application/json");


    }
}
