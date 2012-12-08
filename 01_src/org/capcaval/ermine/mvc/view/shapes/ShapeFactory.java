package org.capcaval.ermine.mvc.view.shapes;

import java.awt.Color;

import org.capcaval.ermine.mvc.view.shapes._impl.j2d.ShapeFactoryImpl;
import org.capcaval.ermine.mvc.view.shapes.geom.CircleShape;
import org.capcaval.ermine.mvc.view.shapes.geom.RectangleShape;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;

public interface ShapeFactory {

	static public ShapeFactory factory = new ShapeFactoryImpl();
	
	public CircleShape newUserCirceShape(int x, int y, int radius);
	public CircleShape newDeviceCirceShape(int x, int y, int radius);
	public CircleShape newUserCirceShape(int x, int y, int radius, FillStyle fs, LineStyle ls);
	public CircleShape newDeviceCirceShape(int x, int y, int radius, FillStyle fs, LineStyle ls);
	
	public RectangleShape newRectangleShape(double x, double y, double width, double height);

	public LineStyle newLineStyle(Color color, int width);
	public FillStyle newFillStyle(Color color);
	public ShapeContainer newShapeContainer(double x, double y);
}
