package org.capcaval.ermine.mvc.view.shapes.event.eventwrapper;

import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeDragAndDropEvent;
import org.capcaval.ermine.mvc.view.shapes.event.technical.MouseDragTechnicalEvent;

public class DragEventWrapper 
    extends EventWrapper
{
    protected MouseDragTechnicalEvent mouseDragTechnicalEvent;
    protected List<ShapeDragAndDropEvent> shapeDragAndDropEventList = new ArrayList<ShapeDragAndDropEvent> ();

    /**
     * Get accessor for shapeDragAndDropEventList
     * @return  value of shapeDragAndDropEventList
     */
    public List<ShapeDragAndDropEvent> getShapeDragAndDropEventList () {
        return this.shapeDragAndDropEventList;
    }

    /**
     * Set accessor for shapeDragAndDropEventList
     * @param value the value to set in shapeDragAndDropEventList
     */
    public void setShapeDragAndDropEventList (List<ShapeDragAndDropEvent>  value) {
        this.shapeDragAndDropEventList = value; 

    }    
    
    /**
     * Get accessor for mouseDragTechnicalEvent
     * @return  value of mouseDragTechnicalEvent
     */
    public MouseDragTechnicalEvent getMouseDragTechnicalEvent () {
        return this.mouseDragTechnicalEvent;
    }

    /**
     * Set accessor for mouseDragTechnicalEvent
     * @param value the value to set in mouseDragTechnicalEvent
     */
    public void setMouseDragTechnicalEvent (MouseDragTechnicalEvent value) {
        this.mouseDragTechnicalEvent = value; 
    }

    protected ShapeDragAndDropEvent shapeDragAndDropEvent;

    /**
     * Get accessor for shapeDragAndDropEvent
     * @return  value of shapeDragAndDropEvent
     */
    public ShapeDragAndDropEvent getShapeDragAndDropEvent () {
        return this.shapeDragAndDropEvent;
    }

    /**
     * Set accessor for shapeDragAndDropEvent
     * @param value the value to set in shapeDragAndDropEvent
     */
    public void setShapeDragAndDropEvent (ShapeDragAndDropEvent value) {
        this.shapeDragAndDropEvent = value; 
    }


    public DragEventWrapper(
        final Shape shape,
        final ShapeDragAndDropEvent shapeDragAndDropEvent,
        final MouseDragTechnicalEvent mouseDragTechnicalEvent)
    {
       	// keep the refs
    	this.shape = shape;
    	this.shapeDragAndDropEvent = shapeDragAndDropEvent;
    	this.mouseDragTechnicalEvent = mouseDragTechnicalEvent;
    	this.shapeDragAndDropEventList.add(shapeDragAndDropEvent);
    }

    public DragEventWrapper(
        final Shape shape)
    {
       	// keep the refs
    	this.shape = shape;
    	this.shapeDragAndDropEventList = new ArrayList<ShapeDragAndDropEvent>();
    }
    
    public void addObserver(
            final ShapeDragAndDropEvent shapeDragAndDropEvent)
        {
        	this.shapeDragAndDropEventList.add(shapeDragAndDropEvent);
        }
    
}
