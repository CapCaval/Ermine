package org.capcaval.ermine.mvc.view.shapes.event;

import java.awt.event.MouseEvent;

public interface ShapeInAndOutEvent 
{

     void mouseEntered(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);

     void mouseExit(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);
}
