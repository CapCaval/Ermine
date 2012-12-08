package org.capcaval.ermine.mvc.view.transformation._test;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;
import org.capcaval.ermine.mvc.view.transformation._impl.AffineTransformationImpl;


public class AffineTransformationTest {
	
	@org.junit.Test
	public void testValue(){
//		Rectangle2D.Double sourceBounds = new Rectangle2D.Double();
//		Rectangle2D.Double destinationBounds = new Rectangle2D.Double();
		
//		AffineTransformation at = 
//			TransformationFactory.factory.createAffineTransformation(sourceBounds, destinationBounds);
//		double scalex = at.getAffineTransform().getScaleX();
	}

	@org.junit.Test
	public void testValue2(){
		Rectangle2D.Double deviceBounds = new Rectangle2D.Double(0,0,400,300);
		Point2D.Double userOrigin = new Point2D.Double(100, 200);
		double userWidth = 200;
		
		AffineTransformation at = new AffineTransformationImpl(
				userOrigin,
				userWidth,
				deviceBounds);
		
		Point2D.Double in = new Point2D.Double(250, 100);
		Point2D.Double out = new Point2D.Double();
		
		out = at.transformPoint(in, out);
		
		System.out.println(at.getAffineTransform() + "  " + out);

		in = new Point2D.Double(150, 150);
		out = at.transformPoint(in, out);
		
		System.out.println(at.getAffineTransform() + "  " + out);

	}

	
}
