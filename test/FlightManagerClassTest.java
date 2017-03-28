package test;
import managers.*;
import models.*;
import dataAccess.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FlightManagerClassTest {
	private FlightManager fm;
	private ArrayList<Flight> results;
	private ArrayList<Flight> noResults;
		
	@Before
	public void setUp() throws Exception {
		fm = new FlightManager(); 
		results = fm.searchFlights("AEY", "REY", "26/03/2017", 4);
		//We are asking for too many available seats so there should be no result
		noResults = fm.searchFlights("AEY", "REY", "26/03/2017", 51); 
	}
	

	@After
	public void tearDown() throws Exception {
		fm = null;
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

}
