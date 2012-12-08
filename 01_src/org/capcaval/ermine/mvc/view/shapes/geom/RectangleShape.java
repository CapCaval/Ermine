package org.capcaval.ermine.mvc.view.shapes.geom;

import org.capcaval.ermine.mvc.view.shapes.Shape;


public interface RectangleShape extends Shape{
	public double getWidth();
	public void setWidth(double width);
	
	public double getHeight();
	public void setHeight(double height);
}
