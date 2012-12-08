package org.capcaval.ermine.mvc.view.views._impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.layers._impl.PaintRequest;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;
import org.capcaval.ermine.mvc.view.transformation._impl.AffineTransformationImpl;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents;
import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents.ViewSizeChangedSubscribe;
import org.capcaval.ermine.mvc.view.views.ViewStatistics;

public class ViewImpl<G> implements View, ViewSizeChangedSubscribe {

	protected String name;
	protected AffineTransformation affineTransformation = null;
	protected Color backgroundColor;
	protected boolean orthographic;
	protected Component displayComponent;
	protected Rectangle2D.Double cameraBound;
	protected PaintRequest paintCommand;
	protected ViewEventsImpl viewEventsImpl;
	protected ViewStatistics viewStatistics;

	protected List<Layer> layerList = new ArrayList<Layer>();
	private Rectangle deviceBound;
	private double topLeftX;
	public double getTopLeftX() {
		return topLeftX;
	}

	public double getTopLeftY() {
		return topLeftY;
	}

	public double getUserWidth() {
		return userWidth;
	}

	private double topLeftY;
	private double userWidth;
	private boolean displayStats;

	class PaintCommandImpl implements PaintRequest {
		@Override
		public void requestPaint() {
			displayComponent.repaint();
		}
	};

	public ViewImpl() {
		this.init();
	}

	protected void init() {
		// create the command paint
		this.paintCommand = new PaintCommandImpl();
		// TODO find a better solution to init affine transform
		// Rectangle2D.Double defaultUser = new Rectangle2D.Double(-100, -100,
		// 200, 200);
		// Rectangle2D.Double defaultDevice = new Rectangle2D.Double(0,0, 800,
		// -600);
		// this.affineTransformation = TransformationFactory.factory
		// .createAffineTransformation(defaultUser, defaultDevice);

		this.viewEventsImpl = new ViewEventsImpl();
		this.orthographic = true;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Rectangle2D.Double getCameraUserBound() {
		// TODO Auto-generated method stub
		return this.cameraBound;
	}

	@Override
	public void setCameraUserBound(Rectangle2D.Double bound) {
		// set the value
		this.cameraBound = bound;
		this.topLeftX = bound.x;
		this.topLeftY = bound.y;
		this.userWidth = bound.width;
		

		Component dc = this.getDisplayComponent();

		if (dc != null) {

			// if Ortho use only the x and calculate the y
			if ((this.orthographic == true) && (dc.getWidth() != 0)) {
				// compute the new camera bound
				this.cameraBound = this.computeOrthographicUserBound(this.cameraBound, 
						dc.getBounds());
			}
		}

		// notify all observers
		this.viewEventsImpl.notifyUserSizeChanged(this.cameraBound);

		// redraw
		if (this.getDisplayComponent() != null) {
			// compute affine transform
			this.computeAffineTransform();

			this.getDisplayComponent().repaint();
		}

	}

	protected Rectangle2D.Double computeOrthographicUserBound(
			Rectangle2D.Double userBound, Rectangle2D deviceBound) {
		// calculate the ratio x/y
		double ratio = deviceBound.getWidth() / deviceBound.getHeight();

		// apply the ratio to the y
		userBound.height = userBound.width / ratio;

		return userBound;
	}

	protected void computeAffineTransform() {
		// compute only if the display component is there
		if (this.displayComponent != null) {
		//if (true) {

			// first retrieve the device width and height
			Rectangle rec = null;
			if (this.displayComponent.getWidth() > 0)
				rec = this.getDisplayComponent().getBounds();
			else
				rec = this.deviceBound;
			
			// and set them inside a double rectangle
			Rectangle.Double deviceBoundingBox = new Rectangle.Double(rec.x,
					rec.y, rec.width, rec.height);


			System.out.println("device  " + deviceBoundingBox);
			System.out.println("camera  " + cameraBound);
			// convert the y of the bounding to to top left corner point

			
			Point2D.Double origin = new Point2D.Double(this.topLeftX, this.topLeftY);
			this.affineTransformation = new AffineTransformationImpl(
					origin, 
					this.userWidth, 
					deviceBoundingBox);
		}
	}

	public Component getDisplayComponent() {
		return this.displayComponent;
	}

	@Override
	public void insertLayer(int index, Layer layer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeLayer(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLayer(Layer layer) {
		this.layerList.add(layer);
		// add the command
		layer.setPaintCommand(this.paintCommand);
		
		// add the display component to the layer if any
		if(this.displayComponent != null){
			layer.setDisplayComponent(this.displayComponent);}

	}

	public void composeAllLayer(G g) {
		if(displayStats == true){
			long time = System.nanoTime();
			this.viewStatistics.time1 = time;
			this.viewStatistics.counter++;
			this.viewStatistics.lastFPSCounter++;
			double timeDiff = (time-this.viewStatistics.lastFPSTime)/(1000d*1000d*1000d);
			System.err.println(timeDiff);
			if( timeDiff > 5){ //5s
				this.viewStatistics.fps = this.viewStatistics.lastFPSCounter/timeDiff;
				this.viewStatistics.lastFPSTime = time;
				this.viewStatistics.lastFPSCounter = 0;
			}
		}
		
		int i=0;
		for (Layer layer : this.layerList) {
			// compose only if the layer is visible
			if (layer.isVisible() == true) {
				RenderInfo info = new RenderInfo(this.affineTransformation,
						new Rectangle(0, 0, getDisplayComponent().getWidth(),
								getDisplayComponent().getHeight()),
						this.cameraBound);

				Renderer<G> renderer = (Renderer<G>)layer.getRenderer();
				if(displayStats == true){
					this.viewStatistics.children[i].counter++;
					this.viewStatistics.children[i].time1 = System.nanoTime();
				}
				renderer.render(info, g);
				if(displayStats == true){
					this.viewStatistics.children[i].time2 = System.nanoTime();
				}
			}
			i++;
		}
		if(displayStats == true){
			this.viewStatistics.time2 = System.nanoTime();
			System.out.println(viewStatistics);
		}
		
		
	}

	@Override
	public List<Layer> getLayerList() {
		// TODO Auto-generated method stub
		return this.layerList;
	}

	@Override
	public void setDisplayComponent(Component component) {
		this.displayComponent = component;
		this.displayComponent.setFocusable(true);
		
		this.displayComponent.addComponentListener(new ComponentAdapter(){
    		@Override
    		public void componentResized(ComponentEvent e){
    			// compute the camera bound
    			// calculate the user bound
    			cameraBound = computeCameraBound(
    					deviceBound,
    					topLeftX, topLeftY, 
    					userWidth);
    			
    			// on resized of the frame recompute the affine transform
    			computeAffineTransform();
    			// and repaint
    			displayComponent.repaint();
    		}
    	});
		
		this.displayComponent.addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F2){
					System.out.println("F2 pressed");
					// toggle the display stats state
					displayStats = !displayStats;
					
					if(displayStats == true){
						// compute the statistic instance
						viewStatistics = computeViewStatistic(layerList);
					}
					
				}
			}
		});
		
		// create the event handler
		this.viewEventsImpl.setDisplayComponent(component);

		// check if the bound needs to be computed
		if (this.cameraBound != null) {
			if(this.orthographic == true){
				this.computeOrthographicUserBound(this.cameraBound, 
						this.displayComponent.getBounds());
			}
		}
		
		// for all the layer set the display component
		for(Layer layer : this.layerList){
			layer.setDisplayComponent(component);
		}
	}


	protected ViewStatistics computeViewStatistic(List<Layer> layerList) {
		ViewStatistics viewStatistics = new ViewStatistics();
		
		viewStatistics.name = this.name;
		viewStatistics.children = new ViewStatistics[layerList.size()];
		int i = 0;
		for(Layer layer : layerList){
			ViewStatistics layerStatistics = new ViewStatistics();
			layerStatistics.name = layer.getName();
			viewStatistics.children[i++]=layerStatistics;
		}
		
		return viewStatistics;
	}

	@Override
	public void subscribe(ViewSizeChangedEvents events) {
		this.viewEventsImpl.subscribe(events);

	}

	@Override
	public void unSubscribe(ViewSizeChangedEvents events) {
		this.viewEventsImpl.unSubscribe(events);
	}

	@Override
	public ViewSizeChangedSubscribe getViewSizeChangedSubscribe() {
		return this;
	}

	@Override
	public AffineTransformation getAffineTransformation() {
		return this.affineTransformation;
	}

	@Override
	public Rectangle getDeviceBound() {
		return this.deviceBound;
	}

	@Override
	public void setDeviceBound(Rectangle bound) {
		this.deviceBound = bound;
	}

	@Override
	public void setCameraUserBound(double topLeftX, double topLeftY,
			double userWidth) {
		// compute the affine transform
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.userWidth = userWidth;

		// compute the device size ratio
		Rectangle deviceBound = this.displayComponent.getBounds();
		
		// calculate the user bound
		this.cameraBound = this.computeCameraBound(
				deviceBound,
				topLeftX, topLeftY, 
				userWidth);
		
		Point2D.Double origin = new Point2D.Double(topLeftX, topLeftY);
		

		Rectangle2D.Double rec = new Rectangle2D.Double(
				deviceBound.getX(), deviceBound.getY(), 
				deviceBound.getWidth(), deviceBound.getHeight());
		
		this.affineTransformation = new AffineTransformationImpl(
				origin, 
				userWidth, 
				rec);
		
		// repaint
		this.getDisplayComponent().repaint();
		
	}
	
	protected Rectangle2D.Double computeCameraBound(Rectangle deviceBound, Rectangle userBound){
		double ratio = deviceBound.getWidth()/deviceBound.getHeight();
		return new Rectangle2D.Double(userBound.getX(), userBound.getY(), userBound.getWidth(), userBound.getWidth()/ratio);
	}
	protected Rectangle2D.Double computeCameraBound(Rectangle deviceBound, double userTopLeft, double userTopRight, double width){
		double ratio = deviceBound.getWidth()/deviceBound.getHeight();
		return new Rectangle2D.Double(userTopLeft, userTopRight, width, width/ratio);
	}

}
