package org.capcaval.ermine.mvc.view.shapes.event.eventwrapper;

import java.util.List;
import java.util.ArrayList;

import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeMouseClickEvent;

public class ClickEventWrapper 
    extends EventWrapper
{
    protected List<ShapeMouseClickEvent> shapeMouseClickEventList = new ArrayList<ShapeMouseClickEvent> ();

    /**
     * Get accessor for shapeMouseClickEvent
     * @return  value of shapeMouseClickEvent
     */
    public List<ShapeMouseClickEvent> getShapeMouseClickEventList () {
        return this.shapeMouseClickEventList;
    }

    /**
     * Set accessor for shapeMouseClickEvent
     * @param value the value to set in shapeMouseClickEvent
     */
    public void setShapeMouseClickEvent (List<ShapeMouseClickEvent>  value) {
        this.shapeMouseClickEventList = value; 

    }


    public ClickEventWrapper(
        final Shape shape)
    {
       	// keep the refs
    	this.shape = shape;
    	this.shapeMouseClickEventList = new ArrayList<ShapeMouseClickEvent>();
    }

    public ClickEventWrapper(
        final Shape shape,
        final ShapeMouseClickEvent shapeMouseClickEvent){
       	// keep the refs
    	this.shape = shape;
    	this.shapeMouseClickEventList = new ArrayList<ShapeMouseClickEvent>();
    	this.shapeMouseClickEventList.add(shapeMouseClickEvent);
    }

    public void addObserver( final ShapeMouseClickEvent shapeMouseClickEvent){
    	this.shapeMouseClickEventList.add(shapeMouseClickEvent);
    }
}
