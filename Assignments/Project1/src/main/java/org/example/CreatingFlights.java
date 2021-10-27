package org.example;



import org.example.Flight;

import java.util.ArrayList;
import java.util.List;

public class CreatingFlights {

    private Flight flight1 = new Flight();
    private Flight flight2 = new Flight();
    private Flight flight3 = new Flight();
    private Flight flight4 = new Flight();

    private List<Flight> listOfAllFlights = new ArrayList<>();


    public List<Flight> flightsList(){

        String [] listOfDepartingCities =  {"Providence", "Los Baños", "Pinon Hills", "Tfuna", "Lumā", "Arnesén"};

        String [] listOfArrivalCities =  {"New York", "Chicago", "Denver", "Philadelphia",
                "Los Angeles", "Phoenix"};


        flight1.setFlightID(1);
        flight1.setStartingCity(listOfDepartingCities[0]);
        flight1.setLandingCity(listOfArrivalCities[0]);

        flight2.setFlightID(2);
        flight2.setStartingCity(listOfDepartingCities[1]);
        flight2.setLandingCity(listOfArrivalCities[1]);

        flight3.setFlightID(3);
        flight3.setStartingCity(listOfDepartingCities[2]);
        flight3.setLandingCity(listOfArrivalCities[2]);

        flight4.setFlightID(4);
        flight4.setStartingCity(listOfDepartingCities[3]);
        flight4.setLandingCity(listOfArrivalCities[3]);


        listOfAllFlights.add(flight1);
        listOfAllFlights.add(flight2);
        listOfAllFlights.add(flight3);
        listOfAllFlights.add(flight4);



        return listOfAllFlights;
    }

}