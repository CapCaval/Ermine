package org.capcaval.ermine.mvc.view.shapes.geom;


import java.awt.Color;
import java.awt.geom.Path2D;

public class DevicePathShape extends InteractiveShape {
	
	
	
	public DevicePathShape(int x, int y, Color c, Double rotationDegree){
		super(x, y, c, rotationDegree);
		
		// define the shape
    	Path2D shapePath = new Path2D.Double();
    	shapePath.moveTo(-10, 20);
    	shapePath.lineTo(60, 25);
    	shapePath.curveTo(60, 25, 80, 0, 60, -25);
    	shapePath.lineTo(-10, -20);
    	shapePath.curveTo(-10, -20, 5, 0, -10, 20);
    	
    	this.shape = shapePath;
    	
		//define the bound
    	this.bound = shapePath.getBounds();
	}
	
	public java.awt.Shape getBound(){
		return this.bound;
	}

}
