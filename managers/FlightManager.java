package managers;
import models.*;

import java.util.Date;

import dataAccess.*;

public class FlightManager {
	private FlightStorage fs;
	private String flightDepart, flightDest, flightD;
	private int availableSeats;
	
	
	public boolean checkLegality(String flightDepart, String flightDest, int availableSeats, Date date){
		if(flightDepart){
			
		}
	}
	
	public Flight searchFlights(){
		
		fs.search(flightDepart, flightDest, flightD, availableSeats);
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