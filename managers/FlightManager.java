package managers;
import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dataAccess.*;
public class FlightManager {
	private FlightStorage fs;

	public FlightManager(FlightStorage fsnew){
		this.fs = fsnew;
	}
	
	public boolean checkLegality(String flightDepart, String flightDest, String flightDate, int availableSeats){
		
		boolean legal = true;
		
		// Check if the departure and destination is legal
		switch(flightDepart){
		case "AEY": 
			if(!(flightDest.equals("REY")) && !(flightDest.equals("GRM"))) legal = false;
			break;
		case "REY": 
			if(!(flightDest.equals("AEY")) && !(flightDest.equals("ISF")) && !(flightDest.equals("EGS"))) legal = false; 
			break;
		case "EGS":
			if (!(flightDest.equals("REY"))) legal = false;
			break;
		case "ISF": 
			if (!(flightDest.equals("REY"))) legal = false; 
			break;
		case "GRM": 
			if (!(flightDest.equals("AEY"))) legal = false;
			break;
		default: legal = false;
		}
		
		// Check if trying to book too many seats or too few
		if(availableSeats < 1 || availableSeats >50) legal = false;
		
		// Check if the date entered is a valid date
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
	    try {
	        df.parse(flightDate);
	    } catch (ParseException e) {
	        legal = false;
	    }
		
		return legal;
	}
	
	public ArrayList<Flight> searchFlights(String flightDepart, String flightDest, String flightD, int availableSeats){
		ArrayList<Flight> results = new ArrayList<>();
		boolean isLegal;
		isLegal = checkLegality(flightDepart, flightDest, flightD, availableSeats);
		
		if(isLegal){
			results = fs.search(flightDepart, flightDest, flightD, availableSeats);
		}else{
			throw new IllegalArgumentException();
			
		}
		return results;
	}
	
	
	
}