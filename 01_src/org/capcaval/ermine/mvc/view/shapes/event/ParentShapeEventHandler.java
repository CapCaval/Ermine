package org.capcaval.ermine.mvc.view.shapes.event;

import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;



/**
  * technical interface
  */
public interface ParentShapeEventHandler 
    extends ShapeEventHandler
{

     void addMouseMovedTechnicalEvent(
        final MoveEventWrapper observer);

     void addMouseDragTechnicalEvent(
        final DragEventWrapper observer);

     void addMouseClickTechnicalEvent(
        final ClickEventWrapper observer);
}
