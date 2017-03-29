package dataAccess;
import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FlightStorageImpl {
	
	// Searches for flights in the database that match the given properties
	public ArrayList<Flight> search(String flightDepart, String flightDest, String flightD, int availableSeats) {
		
		Date flightDate = stringToDate(flightD);
		
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
		ArrayList<Flight> flRes = convertToFlight(rs);
		
		dm.disconnect();
		
		return flRes; //Kannski laga þannig að það skili tómu fylki ef ekkert fannst?
	}
	
	
	
	
	// Creates an AarrayList of Flight objects from the database query results
	private ArrayList<Flight> convertToFlight(ResultSet rs) {
		
		// Create an ArrayList from the results 
		ArrayList<Flight> flightList = new ArrayList<Flight>();
		
		// Iterate through all the results and make a Flight object from each of them
		try {
			while (rs.next()) {
				Flight flight = new Flight(rs.getInt("flightID"), rs.getString("flightDepart"), rs.getString("flightDest"),rs.getString("flightTime"), rs.getString("flightDate"), rs.getInt("availableSeats"), rs.getInt("flightPrice"));
				flightList.add(flight);
				System.out.println("flightID: "+flight.getFlightID());
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
	
	
	
	public static void main(String[] args) {
//		String flightDepart = "AEY";
//		String flightDest = "REY";
//		String flightDate = "16/03/2017";
//		int availableSeats = 4;
//		FlightStorage fs = new FlightStorage(); 
//		Flight[] res = fs.search(flightDepart,flightDest, flightDate, availableSeats);
//		System.out.println(res[0].getFlightID());	
	}	

}


