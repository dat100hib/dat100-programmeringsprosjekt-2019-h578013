package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int MARGIN = 50;		// margin on the sides 
	
	//FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		double maxEle = 0;
		double minEle = 1000;
		for (int i = 0; i<gpspoints.length; i++) {						//Lager en for-lokke som stiger mot gpspoints.length
			maxEle = Math.max(maxEle, gpspoints[i].getElevation());		//verdien. Regner saa ut max og min elevation
			minEle = Math.min(minEle, gpspoints[i].getElevation());
		}
		double eleStep = MAXBARHEIGHT/(maxEle-minEle);					
		
		setColor(0, 0, 255);
		int y, x=MARGIN;												// 
		int mellomrom = 2;												// Gir mellomromm mellom hver soyle
		int bredde = 1;													// Gir bredde mellom soyelene
		for (int i = 0; i<gpspoints.length; i++) {						// For-lokke som defeinerer hoyden til soylen.
			y = (int) ((gpspoints[i].getElevation()-minEle)*eleStep);
			System.out.println(y);
			fillRectangle(x,ybase-y, bredde, y);
			x += mellomrom;												// Legger x til i int mellomrom
		}
	}

}
