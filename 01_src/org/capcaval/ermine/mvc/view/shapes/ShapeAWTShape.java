package org.capcaval.ermine.mvc.view.shapes;

import java.awt.Shape;

public interface ShapeAWTShape 
    extends Shape
{

     void setAWTShape(
        final Shape shape);


     Shape getAWTShape();
}
