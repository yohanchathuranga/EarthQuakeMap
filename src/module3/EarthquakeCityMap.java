package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;


public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();
	    

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

	    for (int i = 0; i<earthquakes.size(); i++){
	    	if (earthquakes.size() > 0) {
		    	PointFeature f = earthquakes.get(i);
		    	Object magObj = f.getProperty("magnitude");
		    	float mag = Float.parseFloat(magObj.toString());
		    	Object locObj = f.getLocation();
		    	markers.add(createMarker(f,mag));
		    	

		    	
		    	}
	    	
	    	map.addMarkers(markers);
	    

	    	
	    }
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    
	    
	    
	    	
	    	
	    		    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    
	    //TODO: Add code here as appropriate
	    
	    
	    
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature, float mag)
	{
		
		// finish implementing and use this method, if it helps.
		SimplePointMarker k = new SimplePointMarker(feature.getLocation());
		if (mag >= 5){
			k.setColor(color(255,0,0));
			k.setRadius(20);
		

		}else if(mag<=4.9 && mag>=4.0){
			k.setColor(color(0,255,255));
			k.setRadius(10);
			
		}else if(mag<4){
			k.setColor(color(0,0,255));
			k.setRadius(5);
			
		}
		
		return k;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		fill(255,255,255);
		rect(10,50,150,165);
		
		//make text for keys
		fill(0, 0, 0);
		textSize(20);
		text("Map Key", 15, 70); 
		textSize(12);
		fill(0, 0, 0);
		text("5.0+ Magnitude", 50, 100);
		fill(0, 0, 0);
		text("4.0+ Magnitude", 50, 140);
		fill(0, 0, 0);
		text("Below 4.0", 50, 180);
		
		//make circles
		fill(255,0,0);
		ellipse(30,100, 30,30);
		fill(0,255,255);
		ellipse(30,140,20,20);
		fill(0,0,255);
		ellipse(30,180,10,10);
		
		
		// Remember you can use Processing's graphics methods here
	
	}
}
