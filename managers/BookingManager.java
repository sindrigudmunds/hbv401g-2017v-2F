package managers;
import java.util.ArrayList;
import models.*;
import dataAccess.*;

public class BookingManager {
	private BookingStorage bs;
	private FlightStorage fs;
	
	public BookingManager() {
		bs = new BookingStorage();
	}
	
	public boolean checkLegality(Booking booking){
		boolean legal = true;
		if(booking.getNrBag()<0) legal = false;
		if(booking.getPassengers().isEmpty()) legal = false;
		if(booking.getFlight().getAvailableSeats() < booking.getPassengers().size()) legal = false;
		
		return legal;
	}
	
	public int createBooking(int flightID, int nrBag, ArrayList<Passenger> passengers, String specialNeeds){
		
		Flight flight = searchFlightID(flightID);
		
		Booking newBooking = new Booking(flight, nrBag, passengers, specialNeeds);
		if(checkLegality(newBooking)){
			newBooking.setBookingID(bs.addBooking(newBooking));
			return newBooking.getBookingID();
		}
		//newBooking.setBookingID(bs.addBooking(newBooking));
		//return newBooking.getBookingID();
		return 0;
	}
	public Flight searchFlightID(int flightID){
		return fs.searchID(flightID);
	}

}
