package org.capcaval.ermine.mvc.view.transformation;

import java.awt.geom.AffineTransform;

/**
  * AffineTransformation provides <code>Transformation</code> services based on java.awt.geom.AffineTransform.
  * 
  */
//TODO add rotation calculation.
public interface AffineTransformation 
    extends Transformation
{

     AffineTransform getAffineTransform();
}
