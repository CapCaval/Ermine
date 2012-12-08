package org.capcaval.ermine.mvc.view.shapes.event._impl;

import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ParentShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;

public class ShapeEventHandlerImpl 
    implements ShapeEventHandler
{
    protected List<ClickEventWrapper> clickEventObserverList = new ArrayList<ClickEventWrapper> ();

    /**
     * Get accessor for clickEventObserverList
     * @return  value of clickEventObserverList
     */
    public List<ClickEventWrapper> getClickEventObserverList () {
        return this.clickEventObserverList;
    }

    protected List<DragEventWrapper> dragEventObserverList = new ArrayList<DragEventWrapper> ();

    /**
     * Get accessor for dragEventObserverList
     * @return  value of dragEventObserverList
     */
    public List<DragEventWrapper> getDragEventObserverList () {
        return this.dragEventObserverList;
    }

    protected List<MoveEventWrapper> moveEventObserverList = new ArrayList<MoveEventWrapper> ();

    /**
     * Get accessor for moveEventObserverList
     * @return  value of moveEventObserverList
     */
    public List<MoveEventWrapper> getMoveEventObserverList () {
        return this.moveEventObserverList;
    }

    protected ParentShapeEventHandler parentEventHandler;
	protected ParentCallBack parentCallBack;


    /**
     * Set accessor for parentEventHandler
     * @param value the value to set in parentEventHandler
     */
    public void setParentEventHandler (ParentShapeEventHandler value) {
        this.parentEventHandler = value; 
    }


    public void addShapeMouseClickEventObserver(
        final ClickEventWrapper observer)
    {
    	// add to the list
    	this.clickEventObserverList.add(observer);
    }

    public void addShapeDragAndDropEventObserver(
        final DragEventWrapper observer)
    {
    	// add to the list
    	this.dragEventObserverList.add(observer);
    }

    public void addShapeInAndOutEventObserver(
        final MoveEventWrapper observer)
    {
    	// add to the list
    	this.moveEventObserverList.add(observer);
    }

    public MoveEventWrapper getMouseMovedTechnicalEvent()
    {
    	// a leaf shape event handler do not manage technical event
    	return null;
    }

    public DragEventWrapper getMouseDragTechnicalEvent()
    {
    	// a leaf shape event handler do not manage technical event
    	return null;
    }

    public ClickEventWrapper getMouseClickTechnicalEvent()
    {
    	// a leaf shape event handler do not manage technical event
    	return null;
    }

    public void removeAllShapeObservers(
        final Shape shape)
    {
    	List<ClickEventWrapper>	 cewList = new ArrayList<ClickEventWrapper>();
    	List<DragEventWrapper>	 dewList = new ArrayList<DragEventWrapper>();
    	List<MoveEventWrapper>	 mewList = new ArrayList<MoveEventWrapper>();

    	// get all the shape to be deleted ------------------------------
    	for(ClickEventWrapper observer : this.clickEventObserverList){
    		if(observer.getShape() == shape){
    			cewList.add(observer);}
    	}
    	for(DragEventWrapper observer : this.dragEventObserverList){
    		if(observer.getShape() == shape){
    			dewList.add(observer);}
    	}
    	for(MoveEventWrapper observer : this.moveEventObserverList){
    		if(observer.getShape() == shape){
    			mewList.add(observer);}
    	}
    	// perform the delete --------------------------------------
    	for(ClickEventWrapper wrap : cewList){
    		this.clickEventObserverList.remove(wrap);
    	}
    	for(DragEventWrapper wrap : dewList){
    		this.dragEventObserverList.remove(wrap);
    	}
    	for(MoveEventWrapper wrap : mewList){
    		this.moveEventObserverList.remove(wrap);
    	}
    }

	@Override
	public void setCallBackToParent(ParentCallBack callback) {
		this.parentCallBack = callback;
		
	}
}
