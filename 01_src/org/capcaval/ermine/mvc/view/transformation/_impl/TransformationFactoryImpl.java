package org.capcaval.ermine.mvc.view.transformation._impl;




import java.awt.geom.Rectangle2D;

import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;
import org.capcaval.ermine.mvc.view.transformation.TransformationFactory;

public class TransformationFactoryImpl 
    implements TransformationFactory
{

    public TransformationFactoryImpl()
    {
    }

    public AffineTransformation createAffineTransformation(
        final Rectangle2D.Double sourceBounds,
        final Rectangle2D.Double destinationBounds)
    {
    	return new AffineTransformationImpl( sourceBounds, destinationBounds) ;
    }
}
