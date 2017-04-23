package managers;
import models.*;
import dataAccess.*;

public class BookingManager {
	private BookingStorage bs;
	private FlightStorageImpl fs;
	
	public BookingManager() {
		bs = new BookingStorage();
		fs = new FlightStorageImpl();
	}
	
	public boolean checkLegality(Booking booking){
		boolean legal = true;
		if(booking.getNrBag()<0) legal = false;
		if(booking.getFlight().getAvailableSeats() < (booking.getNrAdult()+booking.getNrChildren()) ) legal = false;
		
		return legal;
	}
	
	public int createBooking(int flightID, int nrBag, int nrAdult, int nrChildren, String specialNeeds){
		Flight flight = fs.searchID(flightID);
		Booking newBooking = new Booking(flight, nrBag, nrAdult, nrChildren, specialNeeds);
		if(checkLegality(newBooking)){
			newBooking.setBookingID(bs.addBooking(newBooking));
			return newBooking.getBookingID();
		}
		//newBooking.setBookingID(bs.addBooking(newBooking));
		//return newBooking.getBookingID();
		return 0;
	}
	public void deleteBooking(int bookingID){
		bs.removeBooking(bookingID);
	}
}
