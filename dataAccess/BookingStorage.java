package dataAccess;
import models.*;

public class BookingStorage {


	public void addBooking(Booking b) {
	// Add the booking to the database
		
	
		//Minnka fjölda lausra sæta í vélinni um fjölda farþega í bókun
	}
	
	public void removeBooking(Booking b) {
		//ToDo:
		//Eyða þessari bókun úr grunni
		//Auka fjölda lausra sæta í vélinni um fjölda farþega í bókun
	}
	

	// adds "nr" to the amount of available seats in flight number "flightID"
	// updateAvailableSeats(510, -8) books eight seats in the flight with ID 510
	private void updateAvailableSeats(int flightID, int nr) {
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		String sql = 	"UPDATE Flights " +
						"SET availableSeats = availableSeats + " + nr + " " + 
						"WHERE flightID = "+ flightID+ ";";
		System.out.println(sql);
		dm.updateDatabase(sql);
		dm.disconnect();
	}
	
	public static void main(String[] args) {
	// Create the Bookings table in database
//		DatabaseManager dm = new DatabaseManager();
//		dm.connect();
//		String sql = "CREATE TABLE Bookings " +
//				"(bookingID INTEGER NOT NULL, " +
//				"flight INTEGER, " + 
//				"nrAdult INTEGER, " +
//				"nrChildren INTEGER, " +
//				"nrBag INTEGER, " +
//				"totalPrice TEXT, " +
//				"specialNeeds TEXT, " + 
//				"PRIMARY KEY ( bookingID ),"+
//				"FOREIGN KEY (flight) REFERENCES Flights (flightID));";
//		System.out.println(sql);
//		dm.updateDatabase(sql);
//		dm.disconnect();
	}
	
}
