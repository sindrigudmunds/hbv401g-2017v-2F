package test;
import models.*;
import dataAccess.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FlightStorageClassTest {
	private FlightStorage fs;
	private ArrayList<Flight> results;
	private ArrayList<Flight> noResults;
		
	@Before
	public void setUp() throws Exception {
		fs = new FlightStorage(); 
		results = fs.search("AEY", "REY", "26/03/2017", 4);
		//We are asking for too many available seats so there should be no result
		noResults = fs.search("AEY", "REY", "26/03/2017", 51); 
	}
	

	@After
	public void tearDown() throws Exception {
		fs = null;
		results = null;
		noResults = null;
	}
	
	@Test
	public void searchWithResults() {
		assertTrue(results.size() >= 1);
	}

	@Test
	public void searchNoResults() {
		//search should return an empty list if no matching flights are found
		assertTrue(noResults.size() == 0);
	}
	
	@Test
	public void testDeparture() {
		for (Flight flight : results) {
			String dep = flight.getFlightDepart();
			assertEquals("AEY", dep);
		}
	}

	@Test
	public void testDestination() {
		for (Flight flight : results) {
			String dest = flight.getFlightDest();
			assertEquals("REY", dest);
		}
	}
	
	@Test
	public void testDate() {
		//Gera eitthvað til að sjá hvort allar dagsetningar séu innan rétts tímabils
	}
	
	@Test
	public void testAvailableSeats() {
		for (Flight flight : results) {
			int availableSeats = flight.getAvailableSeats();
			assertTrue(availableSeats >= 4);
		}
	}
	
	
	@Test
	public void testNoLocationsFound() {
		//Gera eitthvað til að sjá hvað gerist ef leitað er eftir departure sem á ekkert destination 
		//eða er ekki til í kerfinu
	}
	
	@Test
	public void testEachLocationOnce() {
		//Gera eitthvað til að sjá hvort einhverjum destination sé skilað tvisvar
	}
	
	
	@Test
	public void testCorrectDestinations() {
		//Athuga hvort öllum destinations var skilað rétt
	}
	
}
