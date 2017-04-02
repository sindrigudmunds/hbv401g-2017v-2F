package test;
import managers.*;
import models.*;
import dataAccess.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.*;


public class FlightManagerClassTest {
	private FlightStorage fsResults, fsNoResults;
	private FlightManager fmResults, fmNoResults;
		
	@Before
	public void setUp() throws Exception {
		fsResults = new FlightStorageMockResults();
		fsNoResults = new FlightStorageMockNoResults();
		fmResults = new FlightManager(fsResults); 
		fmNoResults = new FlightManager(fsNoResults);
	}

	@After
	public void tearDown() throws Exception {
		fsResults = null;
		fsNoResults = null;
		fmResults = null;
		fmNoResults = null;
	}
	
	@Test
	public void searchWithResults() {
		// Check that the results are returned as an non-empty ArrayList of Flights
		ArrayList<Flight> results = fmResults.searchFlights("AEY", "REY", "26/03/2017", 4);
		assertTrue(results.size() >= 1);
	}

	@Test
	public void searchNoResults() {
		ArrayList<Flight> noResults = fmNoResults.searchFlights("REY", "AEY", "26/03/2017", 5); 
		//search should return an empty array if no matching flights are found
		assertTrue(noResults.size() == 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void throwsExeptionWhenTooManySeats() {
		fmResults.searchFlights("AEY", "REY", "26/03/2017", 51); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void throwsExeptionWhenTooFewSeats() {
		fmResults.searchFlights("AEY", "REY", "26/03/2017", 0); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void throwsExeptionWhenIllegalDate() {
		fmResults.searchFlights("AEY", "REY", "33/03/2017", 10); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void throwsExeptionWhenEmptyDeparture() {
		fmResults.searchFlights("", "REY", "26/03/2017", 10); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void throwsExeptionWhenEmptyDest() {
		fmResults.searchFlights("AEY", "", "26/03/2017", 10); 
	}
	@Test (expected = IllegalArgumentException.class)
	public void throwsExceptionWhenNotAccessibleDest(){
		fmResults.searchFlights("AEY", "ISF", "26/03/2017", 10);
	}
	
	

}
