package org.capcaval.ermine.mvc.view.shapes.event.technical;

import java.awt.event.MouseEvent;

public interface MouseMovedTechnicalEvent 
{

     void mouseMoved(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);
}
