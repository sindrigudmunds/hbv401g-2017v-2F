//package test;
//import models.*;
//import dataAccess.*;
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import managers.PassengerManager;
//
//public class BookingStorageClassTest {
//	public BookingStorage bs;
//	public PassengerManager pm;
//	public Flight flight;
//	public int nrBag;
//	public Passenger p_a, p_b, p_c;
//	public ArrayList<Passenger> passengers;
//	public String specialNeeds;
//	public Booking b;
//
//	
//
//	@Before
//	public void setUp() throws Exception {
//		bs = new BookingStorage();
//		pm = new PassengerManager();
//		flight = new Flight(201, "GRM", "AEY", "15:00", "17/04/2017", 35, 17000);
//		nrBag = 6; 
//		p_a = new Passenger("Jón", true);
//		p_b = new Passenger("Gunna", true);
//		p_c = new Passenger("Óli", false);
//		pm.addPassenger(p_a);
//		pm.addPassenger(p_b);
//		pm.addPassenger(p_c);
//		passengers = pm.getPassengers();
//		specialNeeds = "We are travelling with a dog";
//		b = new Booking(flight, nrBag, passengers, specialNeeds);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		bs = null;
//		flight = null;
//		p_a = null;
//		p_b = null;
//		p_c = null;
//		passengers = null;
//		specialNeeds = null;
//		b = null;
//	}
//
//	@Test
//	public void testIfAddedToDatabase() {
//		int id = bs.addBooking(b);
//		assertTrue(bs.isInDatabase(id));
//	}
//	
//	@Test
//	public void testIfDeletedFromDatabase() {
//		int id = bs.addBooking(b);
//		bs.removeBooking(id);
//		assertFalse(bs.isInDatabase(id));
//	}
//
//}
