package test;
import models.*;
import dataAccess.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FlightStorageClassTest {
	//private FlightManager fm;
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
		//search should return an empty array if no matching flights are found
		assertTrue(noResults.size() == 0);
	}
	
	@Test
	public void testDeparture() {
		assertTrue(results.size() >= 1);
	}

	@Test
	public void testDestination() {
		assertTrue(results.size() >= 1);
	}
	
	@Test
	public void testDate() {
		assertTrue(results.size() >= 1);
	}
	
	@Test
	public void testAvailableSeats() {
		assertTrue(results.size() >= 1);
	}
	
}
