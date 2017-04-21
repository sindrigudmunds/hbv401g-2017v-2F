package models;

import java.util.ArrayList;

public class Booking {
	private int bookingID, totalPrice, nrBag;
	private final int LUGGAGE_PRICE = 1000;
	private Flight flight;
	private String specialNeeds;
	private int nrAdult = 0;
	private int nrChildren = 0;
	private ArrayList<Passenger> passengers;
	
	public Booking(Flight flight, int nrBag, int nrAdult, int nrChildren, String specialNeeds){
		this.flight = flight;
		this.nrBag = nrBag;
		this.nrAdult = nrAdult;
		this.nrChildren = nrChildren;
		this.specialNeeds = specialNeeds;
		this.totalPrice = calcTotalPrice();
	}
	
	//Calculates the total price according to the number of passengers and bags and sets the result as totalPrice
	public int calcTotalPrice(){
		int flightPrice = flight.getFlightPrice();
		int bagPrice = nrBag*LUGGAGE_PRICE;
		this.totalPrice = bagPrice + nrAdult*flightPrice + nrChildren*1/2*flightPrice;
		return this.totalPrice;
	}
	
	public int getNrAdult(){
		return this.nrAdult;
	}
	
	public int getNrChildren(){
		return this.nrChildren;
	}
	
	public int getTotalPrice(){
		return this.totalPrice;
	}
	
	public int getFlightID(){
		return this.flight.getFlightID();
	}
	
	public void setBookingID(int ID){
		this.bookingID = ID;
	}
	
	public int getBookingID(){
		return this.bookingID;
	}
	public String getSpecialNeeds(){
		return this.specialNeeds;
	}
	public ArrayList<Passenger> getPassengers(){
		return this.passengers;
	}
	public int getNrBag(){
		return this.nrBag;
	}
	public Flight getFlight(){
		return this.flight;
	}
}
