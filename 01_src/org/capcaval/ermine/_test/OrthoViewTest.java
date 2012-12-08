package org.capcaval.ermine._test;

import java.awt.Color;
import java.awt.Rectangle;

import org.capcaval.ermine.application.ErmineFrame;
import org.capcaval.ermine.mvc.controller.MouseViewController;
import org.capcaval.ermine.mvc.view.layers.ShapesLayer;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.GridLayer2DImpl;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.ShapeLayer2DImpl;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.ShapeFactory;
import org.capcaval.ermine.mvc.view.shapes._impl.j2d.PolylineJ2DShapeImpl;
import org.capcaval.ermine.mvc.view.shapes._impl.j2d.TextJ2DShapeImpl;
import org.capcaval.ermine.mvc.view.shapes.geom.CircleShape;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.LineStyleImpl;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views._impl.j2d.J2DViewImpl;

public class OrthoViewTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ErmineFrame frame = new ErmineFrame("Java2D test", new Rectangle(0, 0, 410, 370));//330
		View view = new J2DViewImpl();
		frame.addView(view);
		
		ShapeFactory shapeFactory = ShapeFactory.factory;
		
		// add a mouse controller on the view
		new MouseViewController(view);
		
		ShapesLayer shapeLayer  = new ShapeLayer2DImpl();
		view.addLayer(shapeLayer);
		
		LineStyle ls = shapeFactory.newLineStyle(new Color(240, 10, 10, 100), 500);
		FillStyle fs = shapeFactory.newFillStyle(new Color(40, 210, 110, 100));
		
		PolylineJ2DShapeImpl poly = new PolylineJ2DShapeImpl(0, 0, OriginType.USER, null, null);
		PolylineJ2DShapeImpl poly2 = new PolylineJ2DShapeImpl(0, 0, OriginType.DEVICE, null, null);
		poly.setLineStyle(new LineStyleImpl(new Color(240, 10, 10, 200), 10));
		poly2.setLineStyle(new LineStyleImpl(new Color(90, 120, 200, 100), 26));
		
		TextJ2DShapeImpl text = new TextJ2DShapeImpl(100, -100, OriginType.USER, "Ermine");
		TextJ2DShapeImpl text2 = new TextJ2DShapeImpl(200, 100, OriginType.DEVICE, "Ermine :v)");
		

		CircleShape deviceCircle = shapeFactory.newDeviceCirceShape(100, 100, 50);//, fs, ls);
		
		
		shapeLayer.addShape(poly);
		shapeLayer.addShape(poly2);

		shapeLayer.addShape(deviceCircle);

		int deltaY = 0;
		int shapeWidth = 50;
		int shapeNBPL = 10;
		for(int i=0; i<shapeNBPL*shapeNBPL; i++){
			deltaY = i/shapeNBPL;
			CircleShape userCircle = shapeFactory.newUserCirceShape(100 + (i*shapeWidth)%(shapeNBPL*shapeWidth), -100 +(deltaY*shapeWidth), shapeWidth, fs, ls);
			shapeLayer.addShape(userCircle);
		}
		
		
		shapeLayer.addShape(text);
		shapeLayer.addShape(text2);
		
		view.addLayer(new GridLayer2DImpl());
		
		// define where to look at
		view.setCameraUserBound(100d, - 200d, 200d);
		frame.setVisible(true);

	}

}
