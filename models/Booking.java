package models;

import java.util.ArrayList;

public class Booking {
	private int bookingID, totalPrice, nrBag;
	private final int LUGGAGE_PRICE = 1000;
	private Flight flight;
	private String specialNeeds;
	//private int bagPrice = (nrBag*LUGGAGE_PRICE);
	//private int nrOfPassengers;
	private ArrayList<Passenger> passengers;
	
	public int calcprice(){
		int tempPrice = 0;
		int flightPrice = flight.getFlightPrice();
		for(int i=0;i < passengers.size(); i++){
			if(!passengers.get(i).isAdult()){
				tempPrice += flightPrice;
			}else{
				tempPrice += flightPrice*0.5;
			}
		}
		int bagPrice = (nrBag*LUGGAGE_PRICE);
		this.totalPrice = bagPrice + tempPrice;
		return this.totalPrice;
	}
	
}
