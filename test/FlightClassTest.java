package test;
import models.*;

import static org.junit.Assert.*;
import org.junit.*;


//Bara æfing, getum hent þessum klasa í lokin
public class FlightClassTest {
	private Flight fl;
	
	@Before
	public void setUp() throws Exception {
		this.fl = new Flight(500, "AEY", "RVK", "12:00", "12/03/2017", 30, 15000);
	}
		
	@After
	public void tearDown() throws Exception {
		fl = null;
	}

	@Test
	public void testId() {
		assertEquals(500, fl.getFlightID());
	}

}
