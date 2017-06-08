package poster;
import java.util.ArrayList;

import com.cleverfranke.util.FileSystem;

import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PShape;

public class Main extends PApplet {
	
	PeasyCam cam;
	ArrayList<Contour> contours;
	
	public void settings() {
		// Add settings here
		size(1900, 1000, P3D);
		smooth(8);
		
	}
	
	public void setup() {
		
		// Load contours
		ContourLoader cl = new ContourLoader();
		cl.load(
				FileSystem.getApplicationPath("data/contours-nodes.csv"), 
				FileSystem.getApplicationPath("data/contours-attributes.csv"),
				this);
		cl.mapContours(-200, 200, 200, -200);
		contours = cl.getContours();
		
		cam = new PeasyCam(this, 0, 100, 0, 100);
//		cam.setMinimumDistance(0);
//		cam.setMaximumDistance(4000);
		
	}
	
	public void draw() {
		background(0);
		perspective();
		
		rotateX(-.5f);
		rotateY(-.5f);
		background(0);
		
//		stroke(255);
//		noFill();
//		box(10);

		// Draw contours
		stroke(255);
		noFill();
		for (Contour c: contours) {
			PShape s = c.getShape(this);
			s.draw(g);
		}
	}

	public static void main(String[] args) {
		// Program execution starts here
		PApplet.main(Main.class.getName());
	}

}
