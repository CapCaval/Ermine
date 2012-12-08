package org.capcaval.ermine.mvc.view.shapes.event;

import java.awt.event.MouseEvent;

public interface ShapeMouseClickEvent 
{

     void mousePressed(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);

     void mouseRelease(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);
}
