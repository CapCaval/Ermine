package org.capcaval.ermine.mvc.view.layers.shapelayer;


import java.awt.Component;
import java.util.List;

import org.capcaval.ermine.mvc.view.layers.ShapesLayer;
import org.capcaval.ermine.mvc.view.layers._impl.LayerImpl;
import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.ShapeContainer;
import org.capcaval.ermine.mvc.view.shapes._impl.ShapeContainerImpl;


public abstract class ShapeLayerImpl<G> extends LayerImpl<G> implements ShapesLayer {

	static protected int counter =0;
	
	protected ShapeContainer container;
	protected ShapeLayerEventHandler layerEventHandler;
	
	public ShapeLayerImpl(){
		super();
		this.initialize();
	}
	
    private void initialize() {
		this.setName("shape layer#" + counter++);
		
		
		this.container = new ShapeContainerImpl<G>(true, new ParentCallBack() {
			@Override
			public void requestRepaint() {
				// let's repaint
				repaint();
			}
			
			@Override
			public void notifyRequestShapeOnTop(Shape shape) {
				// do nothing
			}
			
			@Override
			public void notifyBoundChanged() {
				// TODO see for bound change 
				//container.computeBound(null);
			}
		});
		// create an event handler
		this.layerEventHandler = new ShapeLayerEventHandler();
		//set it to the shape container to replace the default one
		// and to its children if any
		this.container.setEventHandler(this.layerEventHandler);
		
	}
    
 
	@Override
    public void addShape(Shape shape){
     	// keep the shape
    	this.container.addShape(shape);
    }


	@Override
	public void removeShape(Shape shape) {
		this.container.removeShape(shape);
	}
	
	@Override
	public void setDisplayComponent(Component cmp) {
		// set the default feature
		super.setDisplayComponent(cmp);
		
		// set to the shape layer event handler
		this.layerEventHandler.setEventComponent(cmp);
		
	}
	@Override
	public void setShapeList(List<Shape> shapeList) {
		this.container.setShapeList(shapeList);
	}

    
}
