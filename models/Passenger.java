package models;

public class Passenger {
	private String name;
	private boolean adult;
	
	public Passenger(String name, boolean adult) {
		this.name = name;
		this.adult = adult;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAdult() {
		return adult;
	}
}
