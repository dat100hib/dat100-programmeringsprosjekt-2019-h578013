package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.text.SimpleDateFormat;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {		//Forenklet for-lokke
				if (d < min) {
					min = d;
		}
	}
		return min;
}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		double [] latitudes = new double [gpspoints.length];		// Lager tabellen = gpspoints.length
		for (int i = 0; i < latitudes.length; i++ ) {				// For-lokke som gjør at i går momt latitudes.length
			latitudes[i]=gpspoints[i].getLatitude();				
	}
	return latitudes;
}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double [] longitudes = new double [gpspoints.length];
		for (int i = 0; i < longitudes.length; i++ ) {
			longitudes[i]=gpspoints[i].getLongitude();
	}
	return longitudes;
}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double latitude1, longitude1, latitude2, longitude2;
		
																//finner verdiene for hvert punkt.
		latitude1=Math.toRadians(gpspoint1.getLatitude());
		latitude2=Math.toRadians(gpspoint2.getLatitude());
		longitude1=Math.toRadians(gpspoint1.getLongitude());
		longitude2=Math.toRadians(gpspoint2.getLongitude());
		
																//regner ut og returnerer d ved bruk av Haversine-formlen
				
		double deltaLatitude = latitude2 - latitude1;
		double deltaLongitude = longitude2- longitude1;
		
		double a = Math.pow((Math.sin(deltaLatitude/2)), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLongitude/2), 2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R*c;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		secs=gpspoint2.getTime()-gpspoint1.getTime();			//regner ut punkt til punkt
		speed=(distance(gpspoint1,gpspoint2))/secs*3.6;			//Ganger det med secs
		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		int hh, mm, ss, rest;
		String hhStr, mmStr, ssStr;
		
		hh=secs/3600;	
		rest=secs%3600;							//modulo gir resten av timer
		mm=rest/60;						
		rest=rest%60;							//modulo gir resten av minutter
		ss=rest;
		
		hhStr = hh < 10 ? ("0"+hh) : (""+hh);
		mmStr = mm < 10 ? ("0"+mm) : (""+mm);
		ssStr = ss < 10 ? ("0"+ss) : (""+ss);
		
		timestr=hhStr+TIMESEP+mmStr+TIMESEP+ssStr;
		
		return String.format("%2s%s", "",timestr);
}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		String d_2 = String.format("%.2f",d);
		str = String.format("%10s", d_2).replace(",", ".");

		return str;
	}
}
