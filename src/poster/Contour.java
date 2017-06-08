package poster;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

public class Contour {
	
	int shapeId;
	int id;
	float elevation;
	ArrayList<PVector> points = new ArrayList<>();
	
	public Contour(int shapeId, int id, float elevation) {
		this.shapeId = shapeId;
		this.id = id;
		this.elevation = elevation;
	}

	public int getShapeId() {
		return shapeId;
	}

	public int getId() {
		return id;
	}

	public float getElevation() {
		return elevation;
	}
	
	public void addPoint(PVector point) {
		point.z = elevation;
		points.add(point);
	}
	
	public PShape getShape(PApplet applet) {
		
		PShape shape = applet.createShape();
		shape.beginShape();
		for (PVector p: points) {
			shape.vertex(p.x, p.y, p.z);
		}
		shape.endShape(PConstants.CLOSE);
		
		return shape;
	}
	
	public void mapPoints(float xInMin, float xInMax, float xOutMin, float xOutMax,
			float yInMin, float yInMax, float yOutMin, float yOutMax) {
		for (PVector p: points) {
			p.x = PApplet.map(p.x, xInMin, xInMax, xOutMin, xOutMax);
			p.y = PApplet.map(p.y, yInMin, yInMax, yOutMin, yOutMax);
			
		}
		
	}
	
}
