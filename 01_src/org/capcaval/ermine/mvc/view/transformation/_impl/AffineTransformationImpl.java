package org.capcaval.ermine.mvc.view.transformation._impl;


import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;

/**
  * AffineTransformation implementation class.
  * 
  */
public class AffineTransformationImpl 
    implements AffineTransformation
{
    protected double[] sourcePointTemp = new double[2];

    protected double[] destPointTemp = new double[2];

    protected AffineTransform affineTransform;

    protected boolean debug = true;


    public AffineTransformationImpl(
        final Rectangle2D.Double sourceBounds,
        final Rectangle2D.Double destinationBounds)
    {
		this.initialize(sourceBounds, destinationBounds);
    }
    
    public AffineTransformationImpl(
            final Point2D.Double origin,
            final double userWidth,
            final Rectangle2D.Double destinationBounds){
    	
    	this.affineTransform =  this.computeAffineTransformFromBounds(
    			origin,
    			userWidth,
    			destinationBounds);
    }

    public AffineTransform computeAffineTransformFromBounds(
        final Rectangle2D.Double sourceBounds,
        final Rectangle2D.Double destinationBounds)
    {
    	// allocate an affine transform
		AffineTransform affineTransformation = new AffineTransform();

		// the affine transform formula is (d is destination coordinate, s is source coordinate) :
		// Xd = a.Xu + deltax
		// Yd = b.Yu + deltay

		// calculate a and b of the formula
		// a = WIDTH destination / WIDTH source
		// b = HEIGHT destination / HEIGHT source
		double a = (destinationBounds.width) / sourceBounds.width;
		double b = (destinationBounds.height) / sourceBounds.height;

		// scale factor can not 0
		// if so put it as 1
		if(a==0) a=1;
		if(b==0) b=1;

		// calculate deltaX and deltatY with the above formula :
		// deltax = Xd - aXs
		// deltay = Yd - bYs
		// calculate for the futur device point (0, 0) corresponding to xUser and yUser
		double deltax = destinationBounds.x - (a * sourceBounds.x);
		double deltay = destinationBounds.y - (b * sourceBounds.y);

		affineTransformation.translate(deltax, deltay);
		//affineTransformation.scale(a,b);
		affineTransformation.scale(a,b);

		if(this.debug){
			System.out.print(" a : " + a );
			System.out.print(" b : " + b );
			System.out.print("  deltax : " + deltax );
			System.out.println("  deltay : " + deltay );
		}
		return affineTransformation;
    }

    public AffineTransform computeAffineTransformFromBounds(
            final Point2D.Double origin,
            final double userWidth,
            final Rectangle2D.Double destinationBounds){
    	// allocate an affine transform
		AffineTransform affineTransformation = new AffineTransform();

		
		double scale = destinationBounds.width / userWidth;
		
		double deltax = origin.getX() * -scale;
		double deltay = origin.getY() * scale;
	
		affineTransformation.setTransform(
				scale, 0, 
				0, -scale, 
				deltax, deltay);

		System.out.println(origin.x + "  " + origin.y);
		System.out.println(deltax + "  " + deltay);
		
    	
		return affineTransformation;
        }
    
    
    public Point2D.Double inverseTransformPoint(
        final Point2D.Double inputPoint,
        final Point2D.Double outputPoint)
        throws NoninvertibleTransformException
    {
    	return (Point2D.Double) this.affineTransform.inverseTransform(
    			inputPoint,
    			outputPoint);
    }

    public Point2D.Double transformPoint(
        final Point2D.Double inputPoint,
        final Point2D.Double outputPoint)
    {
    	return (Point2D.Double) this.affineTransform.transform(
    			inputPoint,
    			outputPoint);
    }

    public AffineTransform getAffineTransform()
    {
    	return this.affineTransform;
    }

    /**
      * Initialize AffineTransformationImpl instance.
      */
    protected void initialize(
        final Rectangle2D.Double sourceBounds,
        final Rectangle2D.Double destinationBounds)
    {
    	// calculate the affine transform properties from the bounds
		this.affineTransform =  this.computeAffineTransformFromBounds(
				sourceBounds,
				destinationBounds);
    }
}
