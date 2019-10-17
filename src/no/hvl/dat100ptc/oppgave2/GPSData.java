package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {


		gpspoints = new GPSPoint[antall];		//Oprettet en tabell av GPS-punkt med størrelsen (antall)
		
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;


		if (gpspoints.length != antall) {			// Sjekker at tabellen ikke er lik antall
			gpspoints[antall] = gpspoint;			// Putter inn gpspoint i tabellen
			antall ++;								// Gjør at antallet stiger
			inserted = true;						// Bytter boolean variabelen til true
		
		} 			
		return inserted;							
				
	}

	
	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;
		boolean insert = false;

		int time1 = GPSDataConverter.toSeconds(time);		// Henter time ifra GPSDataConverter
		double latitude1 = Double.parseDouble(latitude);	// Konverterer String til Double
		double longitude1 = Double.parseDouble(longitude);	// Konverterer String til Double	
		double elevation1 = Double.parseDouble(elevation);	// Konverterer String til Double
		
		gpspoint = new GPSPoint (time1, latitude1, longitude1, elevation1);
		insert = insertGPS(gpspoint);						// Henter insertGPS metoden 
		return insert;										// Returnerer insert
}
	
	

	public void print() {

		
		System.out.println("====== Konvertert GPS Data - START ======");
		for (int i = 0; i<antall; i++) {									//En for-lokke 
			System.out.println(gpspoints[i].toString());					//Printer ut listen til gpspoints med lengden i i stringform
		}
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
