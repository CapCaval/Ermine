package org.capcaval.ermine.mvc.controller;


import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.controller.GenericViewControllerEvents.GenericViewControllerEventsSubscribe;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents;
import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents.ViewSizeChangedSubscribe;

public class GenericViewController implements GenericViewControllerEvents, GenericViewControllerEventsSubscribe{
	
	protected double scaleUnit = 10d;
	protected double scrollUnit = 10d;
	private float scale;
	protected View view;
	protected List<GenericViewControllerEvents> observerList = new ArrayList<GenericViewControllerEvents>();
	//private Rectangle2D.Double center;
	
	public GenericViewController(View view){
		// initialiase with default value
		this.init(view, 1.1, 0.2);
	}

	
	public GenericViewController(View view, double scaleUnit, double scrollUnit){
		this.init(view, scaleUnit, scrollUnit);
	}
	
	public void init(View viewObj, double scaleUnit, double scrollUnit) {
		this.scaleUnit = scaleUnit;
		this.scrollUnit = scrollUnit;
		this.view = viewObj;

		ViewSizeChangedSubscribe observable = view.getViewSizeChangedSubscribe();
		observable.subscribe(new ViewSizeChangedEvents(){
			@Override
			public void notifyDeviceSizeChanged(Double deviceBound) {
				if(view.getCameraUserBound() != null) {
					// calculate the new scale
					scale = calculateScale();
					// get the new value to all observers
					notifyScaleChanged(scale);
				}

			}
			@Override
			public void notifyUserSizeChanged(Double deviceBound) {
				// calculate the new scale
				scale = calculateScale();
				// get the new value to all observers
				notifyScaleChanged(scale);
			}});

	}
	
	public void performZoomIn(){
		// get the current visibility bound
		Rectangle2D.Double bound = view.getCameraUserBound();
		
		// calculate it because user coordinate is not supported 
		double centerx = bound.x + bound.width/2;
		double centery = bound.y - bound.height/2;
		
		// calculate the new width and height by applying a zoom factor
		double width = bound.getWidth() / this.scaleUnit;
		double height = bound.getHeight() / this.scaleUnit;
		
		// update the view with its new visibility bound
		view.setCameraUserBound(
				centerx - width/2,
				centery + height/2,
				width);
	}

	
	public void performZoomOut(){
		// get the current visibility bound
		Rectangle2D.Double bound = view.getCameraUserBound();
		
		// calculate it because user coordinate is not supported 
		double centerx = bound.x + bound.width/2;
		double centery = bound.y - bound.height/2;
		
		// calculate the new width and height by applying a zoom factor
		double width = bound.getWidth() * this.scaleUnit;
		double height = bound.getHeight() * this.scaleUnit;
		
		
		// update the view with its new visibility bound
		view.setCameraUserBound(
				centerx - width/2,
				centery + height/2,
				width);
	}

	public void performScrollLeft(){
		// get the current visibility bound
		double x = view.getTopLeftX();
		x = x - this.scrollUnit;
		
		// update the view with its new visibility bound
		view.setCameraUserBound(x, view.getTopLeftY(), view.getUserWidth());
	}
	public void performScrollRight(){
		double x = view.getTopLeftX();
		x = x + this.scrollUnit;
		
		// update the view with its new visibility bound
		view.setCameraUserBound(x, view.getTopLeftY(), view.getUserWidth());
	}
	
	public void performScrollUp(){
		double y = view.getTopLeftY();
		y = y + this.scrollUnit;
		
		// update the view with its new visibility bound
		view.setCameraUserBound(view.getTopLeftX(), y, view.getUserWidth());
	}
	
	public void performScrollDown(){
		double y = view.getTopLeftY();
		y = y - this.scrollUnit;
		
		// update the view with its new visibility bound
		view.setCameraUserBound(view.getTopLeftX(), y, view.getUserWidth());
	}
	
	public double getScaleUnit() {
		return scaleUnit;
	}

	public void setScaleUnit(double scaleUnit) {
		this.scaleUnit = scaleUnit;
	}

	public double getScrollUnit() {
		return scrollUnit;
	}

	public void setScrollUnit(double scrollUnit) {
		this.scrollUnit = scrollUnit;
	}

	public void setScale(float scale) {
		// get the current visibility bound
		Rectangle2D.Double bound = view.getCameraUserBound();
		
		//get the ratio for x
		double widthDevice = view.getDisplayComponent().getWidth();
		double heightDevice = view.getDisplayComponent().getHeight();
		
		// modify the bound if the display component is defined
		if(widthDevice != 0 ||heightDevice != 0){
			// calculate the new width and height by applying a zoom factor
			double userWidth = widthDevice / scale;
			double userHeight = heightDevice / scale;
			
			// create the new bound with the first point as the top left corner
			Rectangle2D.Double newBound = new Rectangle2D.Double(
					bound.getCenterX() - userWidth/2,
					bound.getCenterY() - userHeight/2,
					userWidth,
					userHeight
				);
			
			// update the view with its new visibility bound
			view.setCameraUserBound(newBound);
		}
		else{
			throw new IllegalStateException("The display component is not present therefore it is not possible to calculate and apply the scale. The previous scale command is not applyed. In order to apply the scale the display component has to be dispalyed and or packed.");
		}
	}
	
	public float calculateScale(){
		float scale = 0f;

		// the scale is calculated only on the X axis
		
		// get the current visibility bound
		float userWidth = (float)view.getCameraUserBound().width;
		
		//get the ratio for x
		float deviceWidth = (float)view.getDisplayComponent().getWidth();
		
		// modify the bound if the display component is defined
		if(deviceWidth != 0 ||userWidth != 0){
			scale = deviceWidth/userWidth;
		}
		return scale;
	}

	public float getScale() {
		return this.scale;
	}

	public void setCenter( Rectangle2D.Double center) {
		// get the view visible bound
		Rectangle2D.Double bound = view.getCameraUserBound();
		
		// retrieve the height and width
		double height = bound.getHeight();
		double width = bound.getWidth();
		
		// modify the bound with the new center and keep the width and height 
		bound.setRect(center.x, center.y, width, height);
		
		// apply the new bound
		view.setCameraUserBound(bound);
	}

	public Point2D.Double getCenter() {
		// get the view visible bound
		Rectangle2D.Double bound = view.getCameraUserBound();
		
		// retrieve the x and y of the bound center
		double centerX = bound.getCenterX();
		double centerY = bound.getCenterY();
		
		return new Point2D.Double(centerX, centerY);
	}


	@Override
	public void notifyScaleChanged(float changedScale) {
		for(GenericViewControllerEvents e : this.observerList){
			e.notifyScaleChanged(changedScale);
		}
		
	}


	@Override
	public void subscribe(GenericViewControllerEvents observer) {
		this.observerList.add(observer);
		
	}


	@Override
	public void unsubscribe(GenericViewControllerEvents observer) {
		this.observerList.remove(observer);
	}
	
}
