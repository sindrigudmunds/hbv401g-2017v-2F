package dataAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.*;

public class BookingStorage {

	public int addBooking(Booking b) {
		// Add the booking to the database
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		String sql = "INSERT INTO Bookings(flight, nrAdult, nrChildren, nrBag, totalPrice, specialNeeds)" +
		"VALUES ("+b.getFlightID()+", "+b.getNrAdult()+", "+b.getNrChildren()+", "+b.getNrBag()+", "+b.getTotalPrice()+", '"+b.getSpecialNeeds()+"');";
		int id = dm.updateDatabaseGetID(sql);
		dm.disconnect();
	
		// Reserve seats for the passengers in the chosen flight
		updateAvailableSeats(b.getFlightID(), -(b.getNrAdult()+b.getNrChildren()));
		
		// return the new unique booking id
		return id;
	}
	
	// returns true if there is some booking with this id in the database and false if not
	public boolean isInDatabase(int id) {
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		String sql = "SELECT * FROM Bookings WHERE bookingID = " + id +";";
		ResultSet rs = dm.queryDatabase(sql);
				
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
	
	// Removes the booking with bookingID = id from the database if it exists
	public void removeBooking(int id) {
		// Don't do anything if the booking doesn't exist
		if (!isInDatabase(id)) {
			return;
		} else {
			DatabaseManager dm = new DatabaseManager();
			dm.connect();
			String sql = "SELECT * FROM Bookings Where bookingID = " + id +";";
			
			// Get the flight id and number of passengers from this booking
			int flightID = -1;
			int nrPassengers = -1;
			ResultSet rs = dm.queryDatabase(sql);
			try {
				while(rs.next()) {
					flightID = rs.getInt("flight");
					nrPassengers = (rs.getInt("nrAdult")+rs.getInt("nrChildren"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dm.disconnect();
		
			// Delete the booking
			dm.connect();
			sql = "DELETE FROM Bookings WHERE bookingID="+id+";";
			dm.updateDatabase(sql);
			dm.disconnect();
				
			// Increment the number of available seats in the flight
			if (flightID >= 0 && nrPassengers >= 0) {
				updateAvailableSeats(flightID, nrPassengers);
			}
			
			return;
		}
	}
	
	// adds "nr" to the amount of available seats in flight number "flightID"
	// updateAvailableSeats(510, -8) books eight seats in the flight with ID 510
	private void updateAvailableSeats(int flightID, int nr) {
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		String sql = 	"UPDATE Flights " +
						"SET availableSeats = availableSeats + " + nr + " " + 
						"WHERE flightID = "+ flightID+ ";";
		dm.updateDatabase(sql);
		dm.disconnect();
	}
}
