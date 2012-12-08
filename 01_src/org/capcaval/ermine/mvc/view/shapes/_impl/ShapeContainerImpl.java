package org.capcaval.ermine.mvc.view.shapes._impl;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.ShapeContainer;
import org.capcaval.ermine.mvc.view.shapes.event.ParentShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event._impl.ShapeEventHandlerImpl;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;



public class ShapeContainerImpl <G>
    extends ShapeImpl<G>
    implements ShapeContainer
{
    protected List<Shape> shapeList = new ArrayList<Shape> ();

    //protected ContainerEventHandlerImpl containerEventHandler;
    //protected ShapeContainerImpl.CallBackForChildren callBackForChildren;

    protected boolean root;

    protected double[] matrixValues = new double[6];

    
    public class ShapeContainerPainter 
        implements Renderer<Graphics2D>
    {

//        public void render(
//            final GraphicViewInfo info,
//            final Graphics2D g)
//        {
//        	// TODO do clone only if the affine transform is different
//        	AffineTransform latestAT = containerEventHandler.getLatestAffineTransformUsed();
//        	AffineTransform viewAffinetransform = (AffineTransform)info.getAffineTransform(AffineTransformType.R_S_T);
//        	if((latestAT== null)||(latestAT.equals(viewAffinetransform) == false)){
//            	// set it for event handler in order to manage user event
//        		containerEventHandler.setLatestAffineTransformUsed((AffineTransform)viewAffinetransform);
//        	}
//        	
//        	// draw all the container's shape
//        	for(Shape shape : shapeList){
//        		// create a new context for each shape
//            	Graphics2D g2 = (Graphics2D)g.create();
//            	AffineTransform affinetransform = null;
//            	
//            	// do the needed transformation  
//            	if(shape.getOriginType() == OriginType.USER){
//            		// get view's AffineTRansform
//            		affinetransform = (AffineTransform)viewAffinetransform.clone();//shape.getAffineTransform();
//        		
//            		// for user shape concat the view affinetransform and the shape one
//            		affinetransform.concatenate(shape.getAffineTransform());}
//            	else{ //Device
//            		affinetransform =shape.getAffineTransform();
//            	}
//            	
//        		// apply the affinetransform for each shape
//        		g2.transform(affinetransform);
//            	
//        		// render the shape
//        		shape.getPainter().render(info, g2);
//        		
//            	g2.dispose();
//        	}
//        }

		private double[] matrixValues = new double[6];

		@Override
		public boolean isDirty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void render(RenderInfo info, Graphics2D g) {
        	AffineTransform viewAffinetransform = info.getViewTx().getAffineTransform();
        	AffineTransform backupAffineTRansform = g.getTransform();
        	
        	
        	
        	// draw all the container's shape
        	for(Shape shape : shapeList){
				if (shape.isVisible() == true) {
					// create a new context for each shape
					//Graphics2D g2 = (Graphics2D) g.create();
					Graphics2D g2 =g;

					// special case #1 orientation change on root
					if((shape.getOriginType() == OriginType.USER)&&(root == true)){
							g2.transform(viewAffinetransform);
					}
					// special case #2 orientation change not on root
					else if((getOriginType() == OriginType.USER)&&(
							shape.getOriginType() == OriginType.DEVICE)){
						g2.scale(1/viewAffinetransform.getScaleX(), 1/viewAffinetransform.getScaleY());
					// special case #3 orientation change not on root						
					}else if((getOriginType() == OriginType.DEVICE)&&(
							shape.getOriginType() == OriginType.USER)){
						// change orientation
						g2.scale(1, -1);
					}
					// apply the shape's affinetransform 
					g2.transform(shape.getTransformer().getAffineTransform());

					g2.getTransform().getMatrix(this.matrixValues);
					
					//System.out.println(info.getViewDeltaXPixel() + " " + info.getViewDeltaYPixel());
					
					// store the applied the last rendered affine transform value to the shape 
					shape.getRenderedTx().setTransform(
							this.matrixValues[0], 
							this.matrixValues[1], 
							this.matrixValues[2], 
							this.matrixValues[3], 
							this.matrixValues[4] - info.getViewDeltaXPixel(), 
							this.matrixValues[5] - info.getViewDeltaYPixel());
					
					// render the shape
					Renderer<Graphics2D> p = (Renderer<Graphics2D>)shape.getRenderer();
					p.render(info, g2);

					//g2.dispose();
				}
			}
        	g.setTransform(backupAffineTRansform);
			
		}

		@Override
		public void setDirty(boolean isDirty) {
			// TODO Auto-generated method stub
			
		}
    }

    public class CallBackForChildren 
        implements ParentCallBack
    {

    	@Override
        public void notifyRequestShapeOnTop(
            final Shape shape)
        {
        	// firstly, remove it
        	if(shapeList.remove(shape) == false){
        		throw new NoSuchElementException("The following shape is not handle by the layer : " +  shape);
        	}
        	// secondly, put the shape on the top of the display at the end of the list
        	shapeList.add(shapeList.size(), shape);
        	
        	// cascade the callback to the parent but with itself as shape
        	if( callBackForChildren != null){
        		callBackForChildren.notifyRequestShapeOnTop(ShapeContainerImpl.this);}
        	
        }
    	@Override
        public void notifyBoundChanged(){
        	// send the request to its parent
        	callBackForChildren.notifyBoundChanged();
        }

		@Override
		public void requestRepaint() {
        	// send the request to its parent
			callBackForChildren.requestRepaint();
		}
    }


    /**
      * ShapeContainerJ2DImpl Creator
      */
    public ShapeContainerImpl()
    {
    	// by default a container is not root
    	// root container are only used by layer
    	this.init(false, null);
    }

    /**
      * ShapeContainerJ2DImpl Creator
      */
    public ShapeContainerImpl(
        final boolean isRoot,
        final ParentCallBack callBack)
    {
    	this.init(isRoot, callBack);
    }

    public void addShape(
        final Shape shape)
    {
    	this.shapeList.add(shape);

    	// first retrieve all event from the shape
    	this.retrieveAllEventFromShape(shape);

    	// inform the shape who is its parent, in this case it is itself as container
		shape.setParentCallBack(this.callBackForChildren);
		
		shape.setEventHandler(this.shapeEventHandler);
		
		Area newBound = computeShapeBound(shape);
    }

    public void removeShape(
        final Shape shape)
    {
    	// remove all observer which are linked to the shape
    	//containerEventHandler.removeAllShapeObservers( shape);
    	this.shapeEventHandler.removeAllShapeObservers( shape);
    	
    	this.shapeList.remove(shape);
    	AffineTransform at = shape.getTransformer().getAffineTransform();
    	this.bounds.subtract(shape.getBoundInPixel().createTransformedArea(at));
    }

    public Shape getShape(
        final int id)
    {
    	return this.shapeList.get(id);
    }

    public int getShapeCount()
    {
    	return this.shapeList.size();
    }

    public int getShapeIndex(
        final Shape shape)
    {
    	for(int i = 0 ; i < this.shapeList.size() ; i++){
    		if(this.shapeList.get(i).equals(shape)){
    			return i;
    		}
    	}
    	return -1;
    }

    public void setAllChildrenVisible(
        final boolean visible)
    {
    	for(int i = 0 ; i < this.shapeList.size() ; i++){
    		this.shapeList.get(i).setVisible(visible);
    	}
    }

    public void setAllChildrenEnabled(
        final boolean enable)
    {
    	for(int i = 0 ; i < this.shapeList.size() ; i++){
    		this.shapeList.get(i).setEnable(enable);   			
    	}
    	this.enable = enable;
    }

    public void setEnabled(
        final boolean enable)
    {
    	for(Shape shape : this.shapeList){
    		shape.setEnable(enable);
    	}
    	this.enable = enable;
    }

//    public void setParentEventHandler(
//        final ParentShapeEventHandler parent)
//    {
//       	// for a container this method is called after an add from a container or a layer
//    	// keep a ref of the parent
//    	this.shapeEventHandler.setParentEventHandler(parent);
//    }

    public void retrieveAllEventFromShape(
        final Shape shape)
    {
  	
    	// Drag'n'Drop -----------------------------------------------------------
    	List<DragEventWrapper> obsDragAndDropList = shape.getShapeEventHandler().getDragEventObserverList();
    	if((obsDragAndDropList != null)&&( obsDragAndDropList.size() > 0)){
    		// get all the observer to the layer
    		for(DragEventWrapper obsWrapped : obsDragAndDropList){
    			this.shapeEventHandler.addShapeDragAndDropEventObserver(obsWrapped);
    		}
    	}
    	
    	// In'n'Out -----------------------------------------------------------
    	List<MoveEventWrapper> obsInAndOutList = shape.getShapeEventHandler().getMoveEventObserverList();
    	if((obsInAndOutList != null)&&( obsInAndOutList.size() > 0)){
    		// get all the observer to the layer
    		for(MoveEventWrapper obsWrapped : obsInAndOutList){
    			this.shapeEventHandler.addShapeInAndOutEventObserver(obsWrapped);
    		}
    	}
    	// Click -----------------------------------------------------------
    	List<ClickEventWrapper> obsClickedList = shape.getShapeEventHandler().getClickEventObserverList();
    	if((obsClickedList != null)&&( obsClickedList.size() > 0)){
    		// get all the observer to the layer
    		for(ClickEventWrapper obsWrapped : obsClickedList){
    			this.shapeEventHandler.addShapeMouseClickEventObserver(obsWrapped);
    		}
    	}
    }

    public void setParentMouseShapeEventHandler(
        final ParentShapeEventHandler eventHandler)
    {
    	// for a shape this method is called after an add from a container or a layer
    	// replace the temporary mouseShapeHandler with the parent one
    	// MLB this.containerEventHandler.setParentEventHandler(eventHandler);
    }

    public void retrieveAllEventFrom(
        final Shape shape)
    {
    	
    	// Drag'n'Drop
    	List<DragEventWrapper> obsDragAndDropList = shape.getShapeEventHandler().getDragEventObserverList();
    	if((obsDragAndDropList != null)&&( obsDragAndDropList.size() > 0)){
    		// get all the observer to the layer
    		for(DragEventWrapper obsWrapped : obsDragAndDropList){
    			this.shapeEventHandler.addShapeDragAndDropEventObserver(obsWrapped);
    		}
    	}
    	// In'n'Out
    	List<MoveEventWrapper> obsInAndOutList = shape.getShapeEventHandler().getMoveEventObserverList();
    	if((obsInAndOutList != null)&&( obsInAndOutList.size() > 0)){
    		// get all the observer to the layer
    		for(MoveEventWrapper obsWrapped : obsInAndOutList){
    			this.shapeEventHandler.addShapeInAndOutEventObserver(obsWrapped);
    		}
    	}
    	// Click
    	List<ClickEventWrapper> obsClickedList = shape.getShapeEventHandler().getClickEventObserverList();
    	if((obsClickedList != null)&&( obsClickedList.size() > 0)){
    		// get all the observer to the layer
    		for(ClickEventWrapper obsWrapped : obsClickedList){
    			this.shapeEventHandler.addShapeMouseClickEventObserver(obsWrapped);
    		}
    	}
    }

    public void init(
        final boolean isRoot,
        final ParentCallBack callBack)
    {
    	// set the root state
    	this.root = isRoot;
    	
    	// set the default renderer which is an inner class
    	this.setRenderer(new ShapeContainerPainter());
    	
    	this.shapeEventHandler = new ShapeEventHandlerImpl();
    	
    	
    	// set a custom mouseShapeEvent handler
//    	this.containerEventHandler = new ContainerEventHandlerImpl(
//    			new MoveEventWrapper(this),
//    			new DragEventWrapper(this),
//    			new ClickEventWrapper(this),
//    			isRoot);

    	
    	
    	// set also the ref of the parent class
//    	this.shapeEventHandler = this.containerEventHandler;
    	
    	// allocate a callback for the children
    	this.callBackForChildren = new CallBackForChildren();
    	
    	// set the container as visible
    	this.isVisible = true;
    	
    }

    public void removeShapeFromIDList(
        final int[] idList)
    {
    	for(int shapeId : idList){
    	for(Shape shape :this.shapeList){
		if(shape.getId() == shapeId){
			this.shapeList.remove(shape);
			break;
		}}
	}
    }

	@Override
	public List<Shape> getShapeList() {
		return this.shapeList;
	}

	@Override
	public void setShapeList(List<Shape> shapeList) {
		this.shapeList = shapeList;
	}
	
    public Area computeShapeBound(
            final Shape shape)
        {
       		// get the child bound
    		Area shapeBound = shape.getBoundInPixel();
    		
    		// check out if a transformation is needed, in the case of different origin type
    		if( getOriginType() != shape.getOriginType()){
    			//AffineTransform at = containerEventHandler.getLatestAffineTransformUsed();
    			AffineTransform at = this.getTransformer().getAffineTransform();
    			// compute only a translation affine transform
    			AffineTransform newAt = new AffineTransform();
    			newAt.setToTranslation( at.getTranslateX(), at.getTranslateY());

    			
    			if(getOriginType() == OriginType.USER){
    				// convert the shape bound in user
    				shapeBound = shapeBound.createTransformedArea(newAt);
    			} else{
    				// convert the shape bound in device
    				try {
    					shapeBound = shapeBound.createTransformedArea(newAt.createInverse());
    				} catch (NoninvertibleTransformException e) {
    					e.printStackTrace();
    				}
    			}
    			
    		}
    		return shapeBound;
        }
	@Override
	public void setEventHandler(ShapeEventHandler eventHandler) {
		this.shapeEventHandler = eventHandler;
		for(Shape shape : this.shapeList){
			shape.setEventHandler(this.shapeEventHandler);
			}
	}

}
