package org.capcaval.ermine.mvc.view.transformation;


import java.awt.geom.Rectangle2D;

import org.capcaval.ermine.mvc.view.transformation._impl.TransformationFactoryImpl;

/**
  * Transformation factory.
  */
public interface TransformationFactory 
{
    public static final TransformationFactory factory = new TransformationFactoryImpl();


     AffineTransformation createAffineTransformation(
        final Rectangle2D.Double sourceBounds,
        final Rectangle2D.Double destinationBounds);
}
