package guimodule;

import java.util.HashMap;
import java.util.Map;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;



public class nicksLifeExpectancy extends PApplet {
	
	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	
	public void setup(){
		
		size(800,600,OPENGL);
		map = new UnfoldingMap(this,50,50,700,500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// Load lifeExpectancy data
		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBank.csv");
		
	}
	
	
	
	public void draw(){
		// Draw map tiles and country markers
		map.draw();
	}
	
	private Map<String,Float> loadLifeExpectancyFromCSV(String fileName) {
		Map<String,Float> lifeExpMap = new HashMap<String,Float>();
		String[] rows = loadStrings(fileName);
		for (String row: rows){
			// Reads country name and population density value from CSV row
			// NOTE: Splitting on just a comma is not a great idea here, because
			// the csv file might have commas in their entries, as this one does.  
			// We do a smarter thing in ParseFeed, but for simplicity, 
			// we just use a comma here, and ignore the fact that the first field is split.
			String[] columns = row.split(",");
			
		}
		
		return lifeExpMap;
	}
	
	
	
	
	
}
