package org.capcaval.ermine.mvc.view.transformation;

import java.awt.geom.Point2D;
import java.awt.geom.NoninvertibleTransformException;

public interface Transformation 
{

     Point2D.Double transformPoint(
        final Point2D.Double inputPoint,
        final Point2D.Double outputPoint);

     Point2D.Double inverseTransformPoint(
        final Point2D.Double inputPoint,
        final Point2D.Double outputPoint)
        throws NoninvertibleTransformException;
}
