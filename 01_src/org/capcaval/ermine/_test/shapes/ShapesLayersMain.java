package org.capcaval.ermine._test.shapes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.capcaval.ermine._test.customLayer.CheckBoardLayer;
import org.capcaval.ermine._test.customLayer.TestLayer;
import org.capcaval.ermine.application.ErmineFrame;
import org.capcaval.ermine.mvc.controller.MouseViewController;
import org.capcaval.ermine.mvc.view.layers.ShapesLayer;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.ShapeLayer2DImpl;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes._impl.j2d.RectangleShapeImpl;
import org.capcaval.ermine.mvc.view.shapes._impl.j2d.TextJ2DShapeImpl;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.FillStyleImpl;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.LineStyleImpl;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views._impl.j2d.J2DViewImpl;

public class ShapesLayersMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ErmineFrame frame = new ErmineFrame("Java2D shapes test", new Rectangle(0, 0,
				500, 600));
		View view = new J2DViewImpl();
		
		frame.addView(view);

		// add a checker layer
		CheckBoardLayer cbLayer = new CheckBoardLayer();
		ShapesLayer shapeLayer  = new ShapeLayer2DImpl();
		
		Shape recShape = new RectangleShapeImpl(100, 200, 300, 300, OriginType.USER, null, null);
		Shape recShape2 = new RectangleShapeImpl(100, 200, 300, 300, OriginType.USER, null, null);
		Shape textShape = new TextJ2DShapeImpl(100, 200, OriginType.USER, "Ermine :^) ");
		Shape textShape2 = new TextJ2DShapeImpl(100, 200, OriginType.DEVICE, "Ermine :^) ");
		
		recShape.setLineStyle(new LineStyleImpl(new Color(255, 0, 0, 200), 6));
		recShape.setFillStyle(new FillStyleImpl(new Color(100, 100, 100, 100)));
		
		recShape2.setLineStyle(new LineStyleImpl(Color.DARK_GRAY, 1));
		recShape2.setFillStyle(new FillStyleImpl(new Color(100, 100, 100, 50)));
		
		shapeLayer.addShape(recShape);
		shapeLayer.addShape(recShape2);
		shapeLayer.addShape(textShape);
		shapeLayer.addShape(textShape2);
		
		view.addLayer(cbLayer);
		view.addLayer(shapeLayer);
		
		TestLayer layer = new TestLayer();
		view.addLayer(layer);
		
		// add a mouse controller on the view
		new MouseViewController(view);

		// define where to look at
		view.setCameraUserBound(new Rectangle2D.Double(-100, -100, 1500, 1500));
		
		frame.setVisible(true);
		
	}

}
