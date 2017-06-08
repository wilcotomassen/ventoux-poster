package poster;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.Table;
import processing.data.TableRow;

public class ContourLoader {
	
	private ArrayList<Contour> contours = new ArrayList<>();
	private float minX, maxX, minY, maxY;
	
	public void load(String nodes, String attributes, PApplet applet) {
		
		// Create final list of contours
		contours = new ArrayList<>();
		minX = Float.POSITIVE_INFINITY;
		maxX = Float.NEGATIVE_INFINITY;
		minY = Float.POSITIVE_INFINITY;
		maxY = Float.NEGATIVE_INFINITY;
		
		// Read contours from attributes file
		Table attributeData = applet.loadTable(attributes, "header");
		for (TableRow row : attributeData.rows()) {
			
			// Read data
			int shapeId = row.getInt("shapeid");
			int id = row.getInt("ID");
			float elevation = row.getFloat("ELEV");
			
			// Add contour (without shape data) to tempContours
			contours.add(new Contour(shapeId, id, elevation));
			
		}
		
		// Read shape data from nodes
		Contour currentContour = null;
		Table nodesData = applet.loadTable(nodes, "header");
		for (TableRow row : nodesData.rows()) {
			
			// Read data
			int shapeId = row.getInt("shapeid");
			float x = row.getFloat("x");
			float y = row.getFloat("y");
			
			// Find new matching contour
			if (currentContour == null || currentContour.getShapeId() != shapeId) {
				currentContour = null;
				for (Contour c: contours) {
					if (c.getShapeId() == shapeId) {
						currentContour = c;
						break;
					}
				}
			}
			
			
			if (currentContour != null) {
				
				// Add vertex
				currentContour.addPoint(new PVector(x, y));
				
				// Update limits
				if (x < minX) {
					minX = x;
				}
				if (x > maxX) {
					maxX = x;
				}
				if (y < minY) {
					minY = y;
				}
				if (y > maxY) {
					maxY = y;
				}
			}
			
			
		}
		
	}
	
	public void mapContours(float xMin, float xMax, float yMin, float yMax) {
		
		for (Contour c: contours) {
			c.mapPoints(minX, maxX, xMin, xMax, minY, maxY, yMin, yMax);
		}
		
		// Update limits
		minX = xMin;
		maxX = xMax;
		minY = yMin; 
		maxY = yMax;
	}
	
	public ArrayList<Contour> getContours() {
		return contours;
	}

}
