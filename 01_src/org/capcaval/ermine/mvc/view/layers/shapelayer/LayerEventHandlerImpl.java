package org.capcaval.ermine.mvc.view.layers.shapelayer;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.List;

import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.event.ParentShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;

public class LayerEventHandlerImpl {
    protected MouseMotionListener mouseMotionListener;

    protected MouseListener mouseListener;

    protected MouseWheelListener mouseWheelListener;

    protected Component eventComponent;

    /**
     * Get accessor for eventComponent
     * @return  value of eventComponent
     */
    public Component getEventComponent () {
        return this.eventComponent;
    }

    protected ShapeEventHandler containerEventHandler;

    /**
     * Get accessor for containerEventHandler
     * @return  value of containerEventHandler
     */
    public ShapeEventHandler getContainerEventHandler () {
        return this.containerEventHandler;
    }

    protected Point2D point = new Point2D.Double();

    protected ParentShapeEventHandler eventSubscriber;


    public LayerEventHandlerImpl(
        final ShapeEventHandler containerEventHandler)
    {
		this.containerEventHandler = containerEventHandler;
    }

    public void setEventComponent(
        final Component eventComponent)
    {
		// keep a ref on it
		this.eventComponent = eventComponent;

		// register all the listener on it
		if( this.mouseListener != null){
			this.eventComponent.addMouseListener(this.mouseListener);}
		if( this.mouseMotionListener != null){
			this.eventComponent.addMouseMotionListener(this.mouseMotionListener);}
		if( this.mouseWheelListener != null){
			this.eventComponent.addMouseWheelListener(this.mouseWheelListener);}
    }

    public ParentShapeEventHandler newEventSubscriber()
    {
    	ParentShapeEventHandler eventSubscriber = new ParentShapeEventHandler(){

			@Override public List<ClickEventWrapper> getClickEventObserverList() {return null;}
			@Override public List<DragEventWrapper> getDragEventObserverList() {return null;}
			@Override public List<MoveEventWrapper> getMoveEventObserverList() {return null;}
		
			
			
			@Override public void addMouseMovedTechnicalEvent(MoveEventWrapper observer) {
				if(mouseMotionListener == null){
					mouseMotionListener = newMouseMotionListener();}
				if(eventComponent != null){
					eventComponent.addMouseMotionListener(mouseMotionListener);
				}
			}

			@Override public void addMouseDragTechnicalEvent(DragEventWrapper observer) {
				
			}

			@Override public void addMouseClickTechnicalEvent(ClickEventWrapper observer) {
				if(mouseListener == null){
					mouseListener = newMouseListener();}
				if(eventComponent != null){
					eventComponent.addMouseListener(mouseListener);
				}
			}

			
			@Override public void addShapeMouseClickEventObserver( ClickEventWrapper observer) {}
			@Override public void addShapeDragAndDropEventObserver( DragEventWrapper observer) {}
			@Override public void addShapeInAndOutEventObserver(MoveEventWrapper observer) {}
			@Override public void setCallBackToParent(ParentCallBack callback) {}
			@Override
			public void removeAllShapeObservers(Shape shape) {
				
			}

    	};
    	return eventSubscriber;
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
		return new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent event) {
			}

			@Override
			public void mouseReleased(MouseEvent event) {
			}
		};
    }

    protected MouseWheelListener newMouseWheelListener()
    {
		return new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			}
		};
    }
}
