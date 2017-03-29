package dataAccess;

import java.util.ArrayList;

import models.Flight;

public class FlightStorageMockResults implements FlightStorage {
	
	
	public ArrayList<Flight> search(String flightDepart, String flightDest, String flightD, int availableSeats) {
		
		ArrayList<Flight> flightList = new ArrayList<Flight>();
		
		Flight flight = new Flight(500, "AEY", "REY","12:00", "26/06/2017", 14, 15000);
		flightList.add(flight);
		flight = new Flight(501, "AEY", "REY","12:00", "27/06/2017", 40, 15000);
		flightList.add(flight);
		flight = new Flight(502, "AEY", "REY","16:00", "27/06/2017", 40, 15000);
		flightList.add(flight);
		flight = new Flight(502, "AEY", "REY","16:00", "28/06/2017", 40, 15000);
		flightList.add(flight);
		
		return flightList;
	}


}
