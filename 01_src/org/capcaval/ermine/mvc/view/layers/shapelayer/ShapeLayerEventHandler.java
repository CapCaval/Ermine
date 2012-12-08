package org.capcaval.ermine.mvc.view.layers.shapelayer;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeMouseClickEvent;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;

public class ShapeLayerEventHandler implements ShapeEventHandler{
	// observers lists
    protected List<ClickEventWrapper> clickEventObserverList = new ArrayList<ClickEventWrapper> ();
    protected List<DragEventWrapper> dragEventObserverList = new ArrayList<DragEventWrapper> ();
    protected List<MoveEventWrapper> moveEventObserverList = new ArrayList<MoveEventWrapper> ();
	// component listeners
	protected MouseMotionListener mouseMotionListener;
	protected MouseListener mouseListener;
	protected MouseWheelListener mouseWheelListener;

	protected Component eventComponent;

	public ShapeLayerEventHandler(){
		// create all the observer instances for the component
		this.mouseListener = this.newMouseListener();
		this.mouseMotionListener = this.newMouseMotionListener();
		this.mouseWheelListener = this.newMouseWheelListener();
	}
	
	public void setEventComponent(final Component eventComponent) {
		// keep a reference on it
		this.eventComponent = eventComponent;

		// register all the listener on it
		this.eventComponent.addMouseListener(this.mouseListener);
		this.eventComponent.addMouseMotionListener(this.mouseMotionListener);
		this.eventComponent.addMouseWheelListener(this.mouseWheelListener);
	}
	
	public void addShapeMouseClickEventObserver(final ClickEventWrapper observer) {
		// add to the list
		this.clickEventObserverList.add(observer);
	}

	public void addShapeDragAndDropEventObserver(final DragEventWrapper observer) {
		// add to the list
		this.dragEventObserverList.add(observer);
	}

	public void addShapeInAndOutEventObserver(final MoveEventWrapper observer) {
		// add to the list
		this.moveEventObserverList.add(observer);
	} 
	
    protected MouseMotionListener newMouseMotionListener()
    {
		return new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent event) {
			}

			@Override
			public void mouseMoved(MouseEvent event) {
			}
		};
    }

    protected MouseListener newMouseListener()
    {
    	MouseListener listener =  new MouseAdapter() {
			
			Point2D.Double inPoint = new Point2D.Double();
			Point2D.Double outPoint = new Point2D.Double();

			@Override
			public void mousePressed(MouseEvent event) {
				for(ClickEventWrapper cew : clickEventObserverList){
					// compute the position in shape coordinate
					AffineTransform atx = cew.getShape().getRenderedTx();
					this.inPoint.setLocation(event.getX(), event.getY());
					atx.transform(this.inPoint, this.outPoint);
					
					Area bound = cew.getShape().getBoundInPixel();
					boolean isInside = bound.contains(event.getX(), event.getY());
					
					if(isInside == true){
						for(ShapeMouseClickEvent e : cew.getShapeMouseClickEventList())
							e.mousePressed(0, 0, event);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent event) {
			}
		};
		return listener;
    }

    protected MouseWheelListener newMouseWheelListener()
    {
		return new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			}
		};
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
	}


}
