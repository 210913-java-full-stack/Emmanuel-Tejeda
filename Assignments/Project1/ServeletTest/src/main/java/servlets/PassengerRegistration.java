package servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Vestigial for now

public class PassengerRegistration {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter pw = resp.getWriter();

        resp.setContentType("text/html");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String flightID = req.getParameter("flightID");


        System.out.println("Passenger First Name :: " + firstName);
        System.out.println("Passenger Last Name :: " + lastName);
        System.out.println("The Flight ID :: " + flightID);
    }
}
