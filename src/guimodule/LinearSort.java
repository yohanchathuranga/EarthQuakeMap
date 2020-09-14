package guimodule;

import demos.Airport;

public class LinearSort {
	//toFind is a city name
	public static void main(){
		
	}
	
	public static String findAirportCode(String toFind, Airport[] airports)
	
	{
		
		
		for (int i = 0; i<airports.length;i++){
			if (airports[i].getCity().equals((toFind))){
				return airports[i].getCode3();
				
			}			
		}
		
		
		
		
		
		return null;
	}
	
	
	
}
