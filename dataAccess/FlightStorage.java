package dataAccess;

import java.util.ArrayList;

import models.Flight;

public interface FlightStorage {
	public ArrayList<Flight> search(String flightDepart, String flightDest, String flightD, int availableSeats, boolean flexible);
	public Flight searchID(int flightID);
}
