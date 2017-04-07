package managers;
import models.*;
import java.util.ArrayList;

public class PassengerManager {
	private ArrayList<Passenger> passengers;
	
	public PassengerManager() {
		passengers = new ArrayList<Passenger>();
	}
	
	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}
	
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
}
