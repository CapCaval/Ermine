package org.capcaval.ermine.mvc.view.shapes.event.technical;

import java.awt.event.MouseEvent;

public interface MouseDragTechnicalEvent 
{

     void mouseDragged(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);
}
