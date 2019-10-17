package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	
	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i<gpspoints.length-1; i++) {						// Setter opp en for-lokke som 
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);	// Regner ut den totale distansen
	}
	return distance;
}

	
	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i<gpspoints.length-1; i++) {						// Bruker en lokke til å regne ut 
			if (gpspoints[i].getElevation()<gpspoints[i+1].getElevation()) {// totale hoydemeter
				elevation=gpspoints[i+1].getElevation();
			}	
		}
	return elevation;
	}

	public int totalTime() {

		return (gpspoints[gpspoints.length-1].getTime()-gpspoints[0].getTime());
	}
		

	public double[] speeds() {
		
		double [] speedsTab = new double [gpspoints.length-1];			//Lager en double tabell som tar double minus 
		for (int i = 0; i<gpspoints.length-1; i++) {					//forrige punkt
			speedsTab[i]=GPSUtils.speed(gpspoints[i],gpspoints[i+1]);	//For løkke regner ut gjennomsnitts
																		//Hastighet mellom hvert punkt vi har vert innom.
		}
		return speedsTab;

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		return GPSUtils.findMax(speeds());			//Returnerer den storste hastigheten vi har hatt mellom 2 punkter.
	}

	public double averageSpeed() {

		double average = 0;
		
		return totalDistance()/totalTime()*3.6;		//Gjennomsnittshastigheten gjennom hele ruten.
		
	}


	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjÃ¸res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		if (speedmph<10 && speedmph>0) {					//Den leter etter *met* som er hvor mye kallorier som forbrennes
			met=4.0;
		} else if (10<=speedmph && speedmph<12) {			// Brukte en else if for å få med mange statements
			met=6.0;
		} else if (12<=speedmph && speedmph<14) {
			met=8.0;
		}else if (14<=speedmph && speedmph<16) {
			met=10.0;
		}else if (16<=speedmph && speedmph<20) {
			met=12.0;
		}else if (20<=speedmph) {
			met=16.0;
		}
		
		kcal= met * weight * secs/3600;						// Ganger met med vekt og deler det på timer.
		
		return kcal;
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		return kcal(weight, totalTime(), averageSpeed());	//Beregner antall kalorier forbrent paa hele turen.
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");
		System.out.println("Total Time"+"\t:     "+ GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance"+"\t:"+String.format("%1$12.2f", totalDistance()/1000)+"km");
		System.out.println("Total elevation"+"\t:"+String.format("%1$12.2f", totalElevation())+"m");
		System.out.println("Max speed"+"\t:"+String.format("%1$12.2f", maxSpeed())+"km/t");
		System.out.println("Average speed"+"\t:"+String.format("%1$12.2f", averageSpeed())+"km/t");
		System.out.println("Energy"+"\t\t:"+String.format("%1$14.2f", totalKcal(WEIGHT))+"kcal");
		System.out.println("==============================================");
		
	}

}
