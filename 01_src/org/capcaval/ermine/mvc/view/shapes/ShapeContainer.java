package org.capcaval.ermine.mvc.view.shapes;

import java.util.List;


public interface ShapeContainer
    extends Shape
{

     void addShape(
        final Shape shape);

     void removeShape(
        final Shape shape);

     void removeShapeFromIDList(
        final int[] idList);

     Shape getShape(
        final int id);

     int getShapeCount();

     int getShapeIndex(
        final Shape shape);

     void setAllChildrenVisible(
        final boolean visible);

     void setAllChildrenEnabled(
        final boolean enabled);

	List<Shape> getShapeList();
	void setShapeList(List<Shape> shapeList);
}
