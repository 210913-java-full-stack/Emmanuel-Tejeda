package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CreatingFlights;
import org.example.Flight;
import org.example.NewCustomer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class PingServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("in ping servlet get");
        try {
            CreatingFlights thisFlight = new CreatingFlights();
            List<Flight> allFlights = thisFlight.flightsList();

            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(allFlights));
            resp.setContentType("application/json");
            resp.setStatus(200);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("In ping servlet post");
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
            System.out.println("Hello Post");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}