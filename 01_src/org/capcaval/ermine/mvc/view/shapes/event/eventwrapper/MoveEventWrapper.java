package org.capcaval.ermine.mvc.view.shapes.event.eventwrapper;

import java.util.List;
import java.util.ArrayList;

import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeInAndOutEvent;
import org.capcaval.ermine.mvc.view.shapes.event.technical.MouseMovedTechnicalEvent;

public class MoveEventWrapper 
    extends EventWrapper
{
    protected MouseMovedTechnicalEvent mouseMovedTechnicalEvent;

    /**
     * Get accessor for mouseMovedTechnicalEvent
     * @return  value of mouseMovedTechnicalEvent
     */
    public MouseMovedTechnicalEvent getMouseMovedTechnicalEvent () {
        return this.mouseMovedTechnicalEvent;
    }

    /**
     * Set accessor for mouseMovedTechnicalEvent
     * @param value the value to set in mouseMovedTechnicalEvent
     */
    public void setMouseMovedTechnicalEvent (MouseMovedTechnicalEvent value) {
        this.mouseMovedTechnicalEvent = value; 
    }

    protected List<ShapeInAndOutEvent> shapeInAndOutEventList = new ArrayList<ShapeInAndOutEvent> ();

    /**
     * Get accessor for shapeInAndOutEventList
     * @return  value of shapeInAndOutEventList
     */
    public List<ShapeInAndOutEvent> getShapeInAndOutEventList () {
        return this.shapeInAndOutEventList;
    }

    /**
     * Set accessor for shapeInAndOutEventList
     * @param value the value to set in shapeInAndOutEventList
     */
    public void setShapeInAndOutEventList (List<ShapeInAndOutEvent>  value) {
        this.shapeInAndOutEventList = value; 

    }


    public MoveEventWrapper(
        final Shape shape,
        final ShapeInAndOutEvent shapeInAndOutEvent,
        final MouseMovedTechnicalEvent mouseMovedTechnicalEvent)
    {
       	// keep the refs
    	this.shape = shape;
    	this.mouseMovedTechnicalEvent = mouseMovedTechnicalEvent;
    	this.shapeInAndOutEventList = new ArrayList<ShapeInAndOutEvent>();
    	this.shapeInAndOutEventList.add(shapeInAndOutEvent);
    }

    public MoveEventWrapper(
        final Shape shape)
    {
       	// keep the refs
    	this.shape = shape;
    	this.shapeInAndOutEventList = new ArrayList<ShapeInAndOutEvent>();
    }

    public void addObserver(
        final ShapeInAndOutEvent shapeInAndOutEvent)
    {
    	this.shapeInAndOutEventList.add(shapeInAndOutEvent);
    }
}
