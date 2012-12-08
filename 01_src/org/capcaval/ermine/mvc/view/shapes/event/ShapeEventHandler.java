package org.capcaval.ermine.mvc.view.shapes.event;

import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;

/**
 * technical interface
 */
public interface ShapeEventHandler {

	void addShapeMouseClickEventObserver(final ClickEventWrapper observer);
	void addShapeDragAndDropEventObserver(final DragEventWrapper observer);
	void addShapeInAndOutEventObserver(final MoveEventWrapper observer);
	List<ClickEventWrapper> getClickEventObserverList();
	List<DragEventWrapper> getDragEventObserverList();
	List<MoveEventWrapper> getMoveEventObserverList();
	void setCallBackToParent(final ParentCallBack callback);
	void removeAllShapeObservers(Shape shape);
}
