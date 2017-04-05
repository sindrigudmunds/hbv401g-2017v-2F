package dataAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.*;

public class BookingStorage {


	public int addBooking(Booking b) {
	// Add the booking to the database
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		String sql = "INSERT INTO Bookings(flight, nrAdult, nrChildren, nrBag, totalPrice, specialNeeds)" +
		"VALUES ("+b.getFlightID()+", "+b.getNrAdult()+", "+b.getNrChildren()+", "+b.getNrBag()+", "+b.getTotalPrice()+", '"+b.getSpecialNeeds()+"');";
		System.out.println(sql);
		int id = dm.updateDatabaseGetID(sql);
		System.out.println(id);
		dm.disconnect();
	
	// Reserve seats for the passengers in the chosen flight
		updateAvailableSeats(b.getFlightID(), -(b.getNrAdult()+b.getNrChildren()));
		
		return id;
	}
	
	// returns true if there is some booking with this id in the database and false if not
	public boolean isInDatabase(int id) {
		// Connect to the database
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
				
		// Query the database
		String sql = "SELECT * FROM Bookings Where bookingID = '" + id +";";
				
		ResultSet rs = dm.queryDatabase(sql);
		System.out.println(sql);
				
		// Check if there is a booking with the id
		boolean exists = false;
		try {
			if (rs.next() ) {
				exists = true;
			} else {
				exists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dm.disconnect();
		
		return exists;
	}
	
	public void removeBooking(int id) {
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
//		String sql = "CREATE TABLE Bookings ( " +
//				"bookingID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"flight INTEGER NOT NULL, " + 
//				"nrAdult INTEGER, " +
//				"nrChildren INTEGER, " +
//				"nrBag INTEGER, " +
//				"totalPrice INTEGER, " +
//				"specialNeeds TEXT, " + 
//				"FOREIGN KEY (flight) REFERENCES Flights(flightID));";
//		System.out.println(sql);
//		dm.updateDatabase(sql);
//		dm.disconnect();
		

		// Add a booking to the table
//		DatabaseManager dm = new DatabaseManager();
//		dm.connect();
//		String sql = "INSERT INTO Bookings(flight, nrAdult, nrChildren, nrBag, totalPrice, specialNeeds)" +
//		"VALUES (313, 2, 1, 3, 35000, 'Peanut allergies!');";
//		System.out.println(sql);
//		dm.updateDatabase(sql);
//		dm.disconnect();
		
		
//		DatabaseManager dm = new DatabaseManager();
//		dm.connect();
//		String sql = "INSERT INTO Bookings(flight, nrAdult, nrChildren, nrBag, totalPrice, specialNeeds)" +
//		"VALUES (313, 2, 1, 3, 35000, 'Peanut allergies!');";
//		System.out.println(sql);
//		int id = dm.updateDatabaseGetID(sql);
//		System.out.println(id);
//		dm.disconnect();

	}
	
}
