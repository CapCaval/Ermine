package org.capcaval.ermine.application;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.capcaval.ermine.mvc.controller.MouseViewController;
import org.capcaval.ermine.mvc.view.layers.GridLayer;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.GridLayer2DImpl;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.ShapeLayer2DImpl;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.ShapeContainer;
import org.capcaval.ermine.mvc.view.shapes.ShapeFactory;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views._impl.j2d.J2DViewImpl;

public class ErmineFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536328374634980008L;
	protected DisplayComponent displayComponent;
	protected View view;
	protected Rectangle bound;
	private GridLayer gridLayer;
	private ShapeLayer2DImpl shapeLayer;
	
	class DisplayComponent extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//view.composeAllLayer((Graphics2D)g);
		}

		
	}
	
	public ErmineFrame(String label, Rectangle bound){
		super(label);
		this.init(bound);
	}
	public ErmineFrame(String label, Rectangle deviceBound , double userTopLeftX, double userTopLeftY,
			double userWidth, double gridSize) {
		super(label);
		this.init(deviceBound);
		// create a view 
		View view = new J2DViewImpl();
		//add it to the frame
		this.addView(view);
		
		// add a mouse controller on the view
		new MouseViewController(view);
		
		this.gridLayer = new GridLayer2DImpl();
		this.gridLayer.setGridSizeInUser(gridSize);
		
		// add a gridLayer
		view.addLayer(this.gridLayer);
		
		// add a shape layer
		this.shapeLayer = new ShapeLayer2DImpl();
		view.addLayer(this.shapeLayer);
		
		// define where to look at
		view.setCameraUserBound(userTopLeftX, userTopLeftY, userWidth);
		
	}
	public void init(Rectangle bound){
		// create the display component
		this.displayComponent = new DisplayComponent();
		this.bound = bound;
		this.setBounds(bound);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addView(View view) {
		this.view = view;
		
		view.getDisplayComponent().setBounds(0,0,this.bound.width, this.bound.height);
		this.getContentPane().add(view.getDisplayComponent());
		this.validate();
		// set the display component
		view.setDeviceBound(this.bound);
		//view.setDisplayComponent(this.displayComponent);
		
		
	}
	public void addShape(Shape shape) {
		this.shapeLayer.addShape(shape);
	}		
}
