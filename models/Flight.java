package models;

public class Flight {
	private int flightID;
	private String flightDepart;
	private String flightDest;
	private String flightTime; //  hh:mm 	
	private String flightDate; //  dd/mm/yyyy 	
	private int availableSeats;
	private int flightPrice;
	private final int MAX_SEATS = 50;
	
	// Costructor
	public Flight(int flightID, String flightDepart, String flightDest, String flightTime, String flightDate, int availableSeats, int flightPrice) {
		this.flightID = flightID;
		this.flightDepart = flightDepart;
		this.flightDest = flightDest;
		this.flightTime = flightTime;
		this.flightDate = flightDate;
		this.availableSeats = availableSeats;
		this.flightPrice = flightPrice;
	}
	
	
//Ekki víst að það þurfi þessi föll
	
//	public void bookSeats(int nr) {
//		if (this.availableSeats-nr >= 0) {
//			this.availableSeats = this.availableSeats - nr;
//		} else {
//			//senda eitthvað error?
//		}
//	}
//	
//	public void cancelSeats(int nr) {
//		if (this.availableSeats+nr <= this.MAX_SEATS) {
//			this.availableSeats = this.availableSeats + nr;
//		} else {
//			//senda eitthvað error?
//		}
//	}
	
	public int getFlightID() {
		return this.flightID;
	}
	
	public String getFlightDepart() {
		return this.flightDepart;
	}
	
	public String getFlightDest() {
		return this.flightDest;
	}
	
	public String getFlightTime() {
		return this.flightTime;
	}
	
	public String getFlightDate() {
		return this.flightDate;
	}
	
	public int getAvailableSeats() {
		return this.availableSeats;
	}
	
	public int getFlightPrice() {
		return this.flightPrice;
	}
	public int getMAX_SEATS(){
		return this.MAX_SEATS;
	}
}



