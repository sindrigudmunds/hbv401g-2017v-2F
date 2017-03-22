package dataAccess;
import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FlightStorage {
	
	// Searches for flights in the database that match the given properties
	public Flight[] search(String flightDepart, String flightDest, Date flightDate, int availableSeats) {
		
		// Create strings to be able to return flights on "flexible" dates, 
		// one day before and after the searched date
		Calendar cal = Calendar.getInstance();
		cal.setTime(flightDate);
		cal.add(Calendar.DATE, -1); // get the day before
		String date1 = "'"+dateToString(cal.getTime())+"'";
		String date2 = "'"+dateToString(flightDate)+"'";
		cal.add(Calendar.DATE, 2); // get the day after
		String date3 = "'"+dateToString(cal.getTime())+"'";
		
		// Connect to the database
		DatabaseManager dm = new DatabaseManager();
		dm.connect();
		
		// Query the database
		String sql = "SELECT * FROM Flights Where flightDepart = '" + flightDepart + "'" +
					 " AND flightDest = '"+ flightDest + "'" +
					 " And flightDate IN ("+ date1 +","+ date2 + "," + date3 +")"+
					 " AND availableSeats >= "+ availableSeats+";";
		
		ResultSet rs = dm.queryDatabase(sql);
		System.out.println(sql);
		
		// Change the ResultSet into an array of Flight objects
		Flight[] flRes = convertToFlight(rs);
		
		dm.disconnect();
		
		return flRes; //Kannski laga þannig að það skili tómu fylki ef ekkert fannst?
	}
	
	
	
	
	// Creates an array of Flight objects from the database query results
	public Flight[] convertToFlight(ResultSet rs) {
		
		// First create an ArrayList from the results because we don't know how long the array has to be
		List<Flight> flightList = new ArrayList<Flight>();
		
		// Iterate through all the results and make a Flight object from each of them
		try {
			while (rs.next()) {
				Flight flight = null;
				flight = new Flight(rs.getInt("flightID"), rs.getString("flightDepart"), rs.getString("flightDest"),rs.getString("flightTime"), rs.getString("flightDate"), rs.getInt("availableSeats"), rs.getInt("flightPrice"));
				flightList.add(flight);
				System.out.println("flightID: "+flight.getFlightID());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		// The method has to return an array so the list is moved to an array
		Flight[] flightArr = new Flight[flightList.size()];
		flightArr = flightList.toArray(flightArr);
	
		return flightArr;		
	}
	
	
	
	
//	// Creates Date from a string of the form "dd/MM/yyyy"
//	private Date stringToDate(String str) {
//		Date date = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			sdf.setLenient(false);
//			date = sdf.parse(str);
//			
//		} catch (ParseException e) {
//			System.err.println(e);
//		}
//		return date;
//	}
	
	
	
	// Creates a string in the right format "dd/MM/yyyy" from a Date
	private String dateToString(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = form.format(date);
		return formattedDate;
	}
	

}


