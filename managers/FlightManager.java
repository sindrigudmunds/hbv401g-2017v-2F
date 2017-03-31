package managers;
import models.*;

import java.util.ArrayList;
import java.util.Date;

import dataAccess.*;

public class FlightManager {
	private FlightStorage fs;

	
	public boolean checkLegality(String flightDepart, String flightDest, int availableSeats, Date date){
		
		return false;
	}
	
	public ArrayList<Flight> searchFlights(String flightDepart, String flightDest, String flightD, int availableSeats){
		ArrayList<Flight> results = new ArrayList<>();
		results = fs.search(flightDepart, flightDest, flightD, availableSeats);
		return results;
	}
	
	public void filterLocations(){
		
	}
	
	public Flight getFlightInfo(int id){
		
		return null;
	}
	
	public void addFlight(Flight flight){
		
	}
}