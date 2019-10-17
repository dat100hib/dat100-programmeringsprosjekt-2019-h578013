package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {
	
	// hentet timer minutter og sekunder, så lagt det sammen til sekunder. deretter returnert totalen	
		int secs;
		int hr, min, sec;
		
		String ts = timestr.substring(TIME_STARTINDEX);			//Starter Indexen ved 11
				
		hr = Integer.parseInt(ts.substring(0, 2));				// Henter timen ut av Stringen og bytter til int
		min = Integer.parseInt(ts.substring(3, 5));				// Henter minutter fra Stringen og bytter til int
		sec = Integer.parseInt(ts.substring(6, 8));				// Henter sekundene fra Stringen og bytter til int
		secs = hr * 60 * 60 + min * 60 + sec;					// Regner om til totalt antall sekunder
		
		return secs;
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
 
		
		GPSPoint gpspoint;
	
		int time = toSeconds(timeStr);							// Henter secundene sammenlagt
		double latitude = Double.parseDouble(latitudeStr);		// Konverterer String til Double
		double longitude = Double.parseDouble(longitudeStr);	// Konverterer String til Double
		double elevation = Double.parseDouble(elevationStr);	// Konverterer String til Double
		
		gpspoint = new GPSPoint (time, latitude, longitude, elevation);		//gir verdi til gpspoint
		
		
		return gpspoint;
		
	}
	
}
