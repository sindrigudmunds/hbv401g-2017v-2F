package models;

import java.util.ArrayList;

public class Booking {
	private int bookingID, totalPrice, nrBag;
	private final int LUGGAGE_PRICE = 1000;
	private Flight flight;
	private String specialNeeds;
	private int nrAdult = 0;
	private int nrChildren = 0;
	//private int bagPrice = (nrBag*LUGGAGE_PRICE);
	//private int nrOfPassengers;
	private ArrayList<Passenger> passengers;
	
	//Calculates the total price according to the number of passengers and bags
	public int calcTotalPrice(){
		int tempPrice = 0;
		int flightPrice = flight.getFlightPrice();
		for(int i=0;i < passengers.size(); i++){
			if(!passengers.get(i).isAdult()){
				tempPrice += flightPrice*0.5;
				this.nrChildren ++;
			}else{
				tempPrice += flightPrice;
				this.nrAdult ++;
			}
		}
		int bagPrice = (nrBag*LUGGAGE_PRICE);
		this.totalPrice = bagPrice + tempPrice;
		return this.totalPrice;
	}
	public int getNrAdult(){
		return this.nrAdult;
	}
	public int getNrChildren(){
		return this.nrChildren;
	}
}
