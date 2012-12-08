package org.capcaval.ermine.mvc.view.shapes.event.technical;

import java.awt.event.MouseEvent;

public interface MouseClickTechnicalEvent 
{

     boolean mousePressed(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);

     void mouseReleased(
        final double xInPixel,
        final double yInPixel,
        final MouseEvent event);
}
