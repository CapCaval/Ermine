package org.capcaval.ermine.mvc.view.views._impl;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents;
import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents.ViewSizeChangedSubscribe;

public class ViewEventsImpl implements ViewSizeChangedEvents, ViewSizeChangedSubscribe{

	List<ViewSizeChangedEvents> observerList = new ArrayList<ViewSizeChangedEvents>();
	
	
	public void setDisplayComponent(Component displayComponent){
		// subscribe device 
		displayComponent.addComponentListener(new ComponentAdapter(){
    		@Override
    		public void componentResized(ComponentEvent e){
    			// build the new bound
    			Rectangle2D.Double bound = new Rectangle2D.Double(
    					e.getComponent().getX(),
    					e.getComponent().getY(),
    					e.getComponent().getWidth(),
    					e.getComponent().getHeight());
    			// notify all observers
    			notifyDeviceSizeChanged(bound);
    		}
    	});
		
	}
	
	@Override
	public void subscribe(ViewSizeChangedEvents events) {
		this.observerList.add(events);
	}

	@Override
	public void unSubscribe(ViewSizeChangedEvents events) {
		this.observerList.remove(events);
	}

	@Override
	public void notifyDeviceSizeChanged(Double deviceBound) {
		for(ViewSizeChangedEvents e : this.observerList){
			e.notifyDeviceSizeChanged(deviceBound);
		}
	}

	@Override
	public void notifyUserSizeChanged(Double deviceBound) {
		for(ViewSizeChangedEvents e : this.observerList){
			e.notifyUserSizeChanged(deviceBound);
		}
	}
}
