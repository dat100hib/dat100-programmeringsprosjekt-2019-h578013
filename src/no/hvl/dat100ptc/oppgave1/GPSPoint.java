package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	private int time = 1;								//laget objektvariabler
	private double longitude = 3.0;
	private double latitude = 2.0;
	private double elevation = 5.0;
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {
		super();										//Gir verdi til objektvariablene
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
		this.elevation = elevation;
		

	}

	public int getTime() {
		return time;
		
	}

	public void setTime(int time) {
		this.time = time;

	}

	public double getLatitude() {
		return latitude;
		
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
		
	}

	public double getLongitude() {
		return longitude;
		
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
		
	}

	public double getElevation() {
		return elevation;
		
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
		
	}
	
	public String toString() {
		
		String t = String.valueOf(time);						//Gjør om objektvariablene til String form
		String l1 = String.valueOf(latitude);
		String l2 = String.valueOf(longitude);
		String e = String.valueOf(elevation);
		String str = (t + " (" + l1 + "," + l2 + ") " + e + "\n");
		return str;
		
	}
}
