package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.Color;
import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.ShapeContainer;
import org.capcaval.ermine.mvc.view.shapes.ShapeFactory;
import org.capcaval.ermine.mvc.view.shapes._impl.ShapeContainerImpl;
import org.capcaval.ermine.mvc.view.shapes.geom.CircleShape;
import org.capcaval.ermine.mvc.view.shapes.geom.RectangleShape;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.FillStyleImpl;

public class ShapeFactoryImpl implements ShapeFactory {

	LineStyle defaultLineStyle = new LineStyleJ2DImpl(new Color( 200, 0, 0, 255), 3);
	FillStyle defaultFillStyle =new FillStyleImpl(new Color(255, 255, 0, 200));
	
	@Override
	public CircleShape newUserCirceShape(int x, int y, int radius) {
		return this.newUserCirceShape(
				x, y, 
				radius, 
				null, null);
	}

	@Override
	public RectangleShape newRectangleShape(double x, double y,
			double width, double height) {
		return new RectangleShapeImpl(x, y, width, height);
	}

	@Override
	public CircleShape newDeviceCirceShape(int x, int y, int radius) {
		return this.newDeviceCirceShape(
				x, y, 
				radius, 
				null, null);
	}

	@Override
	public LineStyle newLineStyle(Color color, int width) {
		return new LineStyleJ2DImpl(color, width);
	}

	@Override
	public FillStyle newFillStyle(Color color) {
		return new FillStyleImpl(color);
	}

	@Override
	public CircleShape newUserCirceShape(int x, int y, int radius,
			FillStyle fs, LineStyle ls) {
		return new CircleJ2DShapeImpl(
				x, y, 
				OriginType.USER, 
				radius, 
				fs==null?this.defaultFillStyle:fs, 
				ls==null?this.defaultLineStyle:ls);
	}

	@Override
	public CircleShape newDeviceCirceShape(int x, int y,
			int radius, FillStyle fs, LineStyle ls) {
		return new CircleJ2DShapeImpl(
				x, y, 
				OriginType.DEVICE, 
				radius, 
				fs==null?this.defaultFillStyle:fs, 
				ls==null?this.defaultLineStyle:ls);
	}

	@Override
	public ShapeContainer newShapeContainer(double x, double y) {
		ShapeContainer con =  new ShapeContainerImpl<Graphics2D>();
		con.setPosition(x, y);
		return con;
	}

}
