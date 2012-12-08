package org.capcaval.ermine.mvc.view.layers;


import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.Shape;

public interface ShapesLayer extends Layer{
	public void addShape(Shape shape);
	public void removeShape(Shape shape);
	public Shape getShape(int id);
	public void setShapeList(List<Shape> shapeList);
}
