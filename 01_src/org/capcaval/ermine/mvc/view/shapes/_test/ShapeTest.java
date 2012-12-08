package org.capcaval.ermine.mvc.view.shapes._test;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import org.capcaval.ermine.application.ErmineFrame;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.ShapeContainer;
import org.capcaval.ermine.mvc.view.shapes.ShapeFactory;
import org.capcaval.ermine.mvc.view.shapes._impl.j2d.AwtShapeShapeImpl;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeInAndOutEvent;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeMouseClickEvent;

public class ShapeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ErmineFrame frame = new ErmineFrame(
				"Java2D test", 
				new Rectangle(0, 0, 410, 370), // define the size of the frame
				-200d, 200d, 400d, // define the camera bound
				40 // define the size of the grid layer 
				);
		
		ShapeFactory sf = ShapeFactory.factory;
		ShapeContainer container = sf.newShapeContainer(100, 100);
		container.setOriginType(OriginType.USER);
		
//		RectangleShape rec1 = sf.newRectangleShape(0, 0, 30, 20);
//		rec1.setFillStyle(sf.newFillStyle(Color.ORANGE));
//		RectangleShape rec2 = sf.newRectangleShape(35, 0, 30, 20);
//		rec2.setFillStyle(sf.newFillStyle(Color.cyan));
//		container.addShape(rec1);
//		container.addShape(rec2);
		
		//RectangleShape rec3 = sf.newRectangleShape(0, -25, 30, 20);
		Rectangle2D.Double awtRec = new Rectangle2D.Double(-50, 50, 100, 100);
		AwtShapeShapeImpl rec3 = new AwtShapeShapeImpl(0, -25, awtRec, OriginType.USER, sf.newFillStyle(Color.ORANGE), sf.newLineStyle(Color.DARK_GRAY, 4));
		rec3.getFillStyle().setPainting(Color.BLUE);
		rec3.setLineStyle(sf.newLineStyle(Color.CYAN, 0));
		rec3.setName("My rectangle");
		
		frame.addShape(container);
		frame.addShape(rec3);
		
		rec3.getShapeSubscriber().addShapeInAndOutEventObserver(new ShapeInAndOutEvent() {
			@Override
			public void mouseExit(double xInPixel, double yInPixel, MouseEvent event) {
				System.out.println("out");
			}
			
			@Override
			public void mouseEntered(double xInPixel, double yInPixel, MouseEvent event) {
				System.out.println("in");
			}
		});

		rec3.getShapeSubscriber().addShapeMouseClickEventObserver(new ShapeMouseClickEvent() {
			@Override
			public void mouseRelease(double xInPixel, double yInPixel, MouseEvent event) {
				System.out.println("mouseRelease");
			}
			
			@Override
			public void mousePressed(double xInPixel, double yInPixel, MouseEvent event) {
				System.out.println("mousePressed");
			}
		});

		
		frame.setVisible(true);
	}

}
