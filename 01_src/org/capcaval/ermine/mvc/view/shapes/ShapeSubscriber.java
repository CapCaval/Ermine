package org.capcaval.ermine.mvc.view.shapes;

import org.capcaval.ermine.mvc.view.shapes.event.ShapeDragAndDropEvent;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeInAndOutEvent;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeMouseClickEvent;

public interface ShapeSubscriber {
	public void addShapeMouseClickEventObserver( final ShapeMouseClickEvent observer);
	public void removeShapeMouseClickEventObserver( final ShapeMouseClickEvent observer);
	public void addShapeDragAndDropEventObserver( final ShapeDragAndDropEvent observer);
	public void removeShapeDragAndDropEventObserver( final ShapeDragAndDropEvent observer);
	public void addShapeInAndOutEventObserver( final ShapeInAndOutEvent observer);
	public void removeShapeInAndOutEventObserver( final ShapeInAndOutEvent observer);

}
