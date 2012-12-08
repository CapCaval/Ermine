package org.capcaval.ermine.mvc.view.shapes.event._impl;

import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;

public class ShapeEventImpl implements ShapeEventHandler{
	// observers lists
    protected List<ClickEventWrapper> clickEventObserverList = new ArrayList<ClickEventWrapper> ();
    protected List<DragEventWrapper> dragEventObserverList = new ArrayList<DragEventWrapper> ();
    protected List<MoveEventWrapper> moveEventObserverList = new ArrayList<MoveEventWrapper> ();

    @Override
	public void addShapeMouseClickEventObserver(ClickEventWrapper observer) {
    	this.clickEventObserverList.add(observer);
	}
	@Override
	public void addShapeDragAndDropEventObserver(DragEventWrapper observer) {
		this.dragEventObserverList.add(observer);
	}
	@Override
	public void addShapeInAndOutEventObserver(MoveEventWrapper observer) {
		this.moveEventObserverList.add(observer);
	}
	@Override
	public List<ClickEventWrapper> getClickEventObserverList() {
		return this.clickEventObserverList;
	}
	@Override
	public List<DragEventWrapper> getDragEventObserverList() {
		return this.dragEventObserverList;
	}
	@Override
	public List<MoveEventWrapper> getMoveEventObserverList() {
		return this.moveEventObserverList;
	}
	@Override
	public void setCallBackToParent(ParentCallBack callback) {
	}
	@Override
	public void removeAllShapeObservers(Shape shape) {
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

}
