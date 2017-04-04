package managers;
import java.util.ArrayList;

import models.*;
public class BookingManager {
	
	public boolean checkLegality(Flight flight, int nrBag, ArrayList<Passenger> passengers, String specialNeeds){
		boolean legal = true;
		
		if(nrBag<0) legal = false;
		if(passengers.isEmpty()) legal = false;
		if(flight.getAvailableSeats() < passengers.size()) legal = false;
		
		return legal;
	}
	public void createBooking(Flight flight, int nrBag, ArrayList<Passenger> passengers, String specialNeeds){
		
		Booking booking = new Booking(flight, nrBag, passengers, specialNeeds);
	}
}
