package org.capcaval.ermine.mvc.view.shapes.event;

import java.awt.event.MouseEvent;

public interface ShapeDragAndDropEvent 
{

     void mouseDragged(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);

     void mouseDropped(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);
}
