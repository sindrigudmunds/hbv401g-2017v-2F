package managers;
import models.*;

import java.util.ArrayList;
//import java.util.Date;

import dataAccess.*;

public class FlightManager {
	private FlightStorage fs;

	public FlightManager(FlightStorage fsnew){
		this.fs = fsnew;
	}
	
	public boolean checkLegality(String flightDepart, String flightDest, String flightD, int availableSeats){
		
		boolean legal = true;
		
		switch(flightDepart){
		case "AEY": 
			if(!flightDest.equals("REY") || !flightDest.equals("GRM")) legal = false;
			break;
		case "REY": 
			if(!flightDest.equals("AEY") || !flightDest.equals("ISF")||!flightDest.equals("EGS")) legal = false; 
			break;
		case "EGS":
			if (!flightDest.equals("REY")) legal = false;
			break;
		case "ISF": 
			if (!flightDest.equals("REY")) legal = false; 
			break;
		case "GRM": 
			if (!flightDest.equals("AEY")) legal = false;
			break;
		default: legal = false;
		}
		if(availableSeats < 1 || availableSeats >50) legal = false;
		
		return legal;
	}
	
	public ArrayList<Flight> searchFlights(String flightDepart, String flightDest, String flightD, int availableSeats){
		if(!checkLegality(flightDepart, flightDest, flightD, availableSeats)){
			throw new
			IllegalArgumentException();
		}
		ArrayList<Flight> results = new ArrayList<>();
		results = fs.search(flightDepart, flightDest, flightD, availableSeats);
		
		return results;
	}
}