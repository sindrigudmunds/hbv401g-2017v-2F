package dataAccess;
import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FlightStorageImpl implements FlightStorage {
	
	// Searches for flights in the database that match the given properties
	public ArrayList<Flight> search(String flightDepart, String flightDest, String flightD, int availableSeats) {
		// Create strings to be able to return flights on "flexible" dates, 
		// one day before and after the searched date
		ArrayList<String> dates = flexibleDates(flightD,3);
		String flexDates = "";
		for(int i = 0; i<(dates.size()-1); i++) {
			flexDates = flexDates + dates.get(i)+","; 
		}
		flexDates = flexDates + dates.get(dates.size()-1);
		System.out.println(flexDates);
			
		// Connect to the database
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		
		// Query the database
		String sql = "SELECT * FROM Flights Where flightDepart = '" + flightDepart + "'" +
					 " AND flightDest = '"+ flightDest + "'" +
					 " And flightDate IN ("+ flexDates +")"+
					 " AND availableSeats >= "+ availableSeats+";";
		
		ResultSet rs = dm.queryDatabase(sql);		
		
		// Change the ResultSet into an ArrayList of Flight objects
		ArrayList<Flight> flRes = convertToFlight(rs);
		dm.disconnect();
		
		return flRes; 
	}
	
	// Creates strings to be able to return flights on "flexible" dates, 
	// "flex" days before and after the searched date
	public ArrayList<String> flexibleDates(String flightD, int flex) {
		ArrayList<String> dates = new ArrayList<String>();
		Date flightDate = stringToDate(flightD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(flightDate);
		cal.add(Calendar.DATE, -(flex+1)); 
		for(int i = 0; i<=2*flex; i++) {
			cal.add(Calendar.DATE, 1);
			String date = "'"+dateToString(cal.getTime())+"'";
			dates.add(date);
		}
		return dates;
	}
	
	// Creates an AarrayList of Flight objects from the database query results
	private ArrayList<Flight> convertToFlight(ResultSet rs) {
		ArrayList<Flight> flightList = new ArrayList<Flight>();
		// Iterate through all the results and make a Flight object from each of them
		try {
			while (rs.next()) {
				Flight flight = new Flight(rs.getInt("flightID"), rs.getString("flightDepart"), rs.getString("flightDest"),rs.getString("flightTime"), rs.getString("flightDate"), rs.getInt("availableSeats"), rs.getInt("flightPrice"));
				flightList.add(flight);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return flightList;		
	}
	
//	Creates Date from a string of the form "dd/MM/yyyy"
	private Date stringToDate(String str) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			date = sdf.parse(str);
			
		} catch (ParseException e) {
			System.err.println(e);
		}
		return date;
	}
	
	// Creates a string in the right format "dd/MM/yyyy" from a Date
	private String dateToString(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = form.format(date);
		return formattedDate;
	}
}


