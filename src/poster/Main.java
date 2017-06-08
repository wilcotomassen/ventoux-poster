package poster;
import java.util.ArrayList;

import com.cleverfranke.util.FileSystem;

import processing.core.PApplet;
import processing.core.PShape;

public class Main extends PApplet {
	
	public void settings() {
		// Add settings here
		size(1900, 1000);
		
	}
	
	public void setup() {
		
		// Load contours
		ContourLoader cl = new ContourLoader();
		cl.load(
				FileSystem.getApplicationPath("data/contours-nodes.csv"), 
				FileSystem.getApplicationPath("data/contours-attributes.csv"),
				this);
		cl.mapContoursToRect(0, 0, width, height);
		ArrayList<Contour> contours = cl.getContours();
		
		background(0);
		
		// Draw contours
		stroke(255);
		noFill();
		for (Contour c: contours) {
			PShape s = c.getShape(this);
			s.draw(g);
		}

		
		noLoop();
	}
	
	public void draw() {}

	public static void main(String[] args) {
		// Program execution starts here
		PApplet.main(Main.class.getName());
	}

}
