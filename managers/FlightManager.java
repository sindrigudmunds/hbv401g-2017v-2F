package managers;
import models.*;

import java.util.Date;

import dataAccess.*;

public class FlightManager {
	
	public Flight searchFlights(){
		String flightDepart = "AEY";
		String flightDest = "RVK";
		String flightDate = "02/04/2017";
		int availableSeats = 4;
		FlightStorage.search(flightDepart, flightDest, flightDate, availableSeats);
		return null;
	}
	
	public void filterLocations(){
		
	}
	
	public Flight getFlightInfo(int id){
		
		return null;
	}
	
	public void addFlight(Flight flight){
		
	}
}