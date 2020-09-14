package guimodule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyDisplay extends PApplet{
	
	
	PImage img;

	public void setup(){
		//Add setup code for MyDispay
		size(400,400);//set canvas size
		background(255);//set canvas color
		stroke(0);//set pen color
		img = loadImage("palmTrees.jpg", "jpg");
		img.resize(0, height);//resize loaded image to full height of canvas
		image(img,0,0);//display image 
	
	}
	
	public void draw(){
		//Add drawing code for MyPApplet
		int[] color = sunColorSec(second());		//calculate color code for sun
		fill(color[0],color[1],color[2]);	//set sun color
		ellipse(width/4,height/5,width/4,height/5);	//draw sun
		fill(255,0,0);
		box(300,300,400);
			}
	
	
/** Return the RGB color of the sun at this number of seconds in the minute */
	
	public int[] sunColorSec(float seconds) {
		
		int[] rgb = new int[3];
		
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		
		return rgb;
		
	}
	
	public static void main (String[] args){
		PApplet.main(new String[] {"--present", "MyDisplay"});
		}
	
	
}
