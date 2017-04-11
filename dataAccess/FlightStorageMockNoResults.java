package dataAccess;

import java.util.ArrayList;

import models.Flight;

public class FlightStorageMockNoResults implements FlightStorage {

	public ArrayList<Flight> search(String flightDepart, String flightDest, String flightD, int availableSeats, boolean flexible) {
		ArrayList<Flight> fl = new ArrayList<Flight>();
		return fl;
	}

}
