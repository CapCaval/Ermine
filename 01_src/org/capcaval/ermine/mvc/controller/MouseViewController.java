package org.capcaval.ermine.mvc.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;
import org.capcaval.ermine.mvc.view.views.View;


public class MouseViewController {

	protected GenericViewController gvc = null;
	protected boolean isMiddleButtonPressed = false;
	protected boolean draggingModeStarting = false;
	Point2D.Double userStartDraggingPoint = new Point2D.Double();

	public MouseViewController(View view) {
		this.gvc = new GenericViewController(view);

		// create the mouse listener
		MouseWheelListener mwl = this.createMouseWheelListener(view);
		MouseMotionListener mml = this.createMouseMotionListener(view);
		MouseListener ml = this.createMouseListener(view);

		// set a listener on the view
		view.getDisplayComponent().addMouseWheelListener(mwl);
		view.getDisplayComponent().addMouseMotionListener(mml);
		view.getDisplayComponent().addMouseListener(ml);
	}

	private MouseListener createMouseListener(final View view) {
		MouseListener ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON2)
					isMiddleButtonPressed = true;
				else
					isMiddleButtonPressed = false;
				
				AffineTransformation at = view.getAffineTransformation();
				
				Point2D.Double device = new Point2D.Double(me.getX(), me.getY());
				Point2D.Double user = new Point2D.Double();
				try {
					at.inverseTransformPoint(device, user);
				} catch (NoninvertibleTransformException e) {
					e.printStackTrace();
				}
				
				System.out.println();
				System.out.println("DEVICE - x : " + device.getX() + "  y : " + device.getY());
				System.out.println("USER - x : " + user.getX() + "  y : " + user.getY());
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				isMiddleButtonPressed = false;
				draggingModeStarting = false;
			}
		};

		return ml;
	}

	protected MouseWheelListener createMouseWheelListener(final View view) {
		MouseWheelListener mwl = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent mwe) {
				int notches = mwe.getWheelRotation();
				if (notches < 0) {
					// Mouse wheel moved UP
					gvc.performZoomIn();
				} else {
					// Mouse wheel moved DOWN
					gvc.performZoomOut();
				}

			}
		};
		return mwl;
	}

	protected MouseMotionListener createMouseMotionListener(final View view) {
		MouseMotionListener mml = new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (isMiddleButtonPressed == true) {
					System.out.println("mouseDragged : " + e.getX() + " "
							+ e.getY());
					AffineTransformation at = view.getAffineTransformation();

					// convert from device to user coordinate
					Point2D.Double userPoint = new Point2D.Double();
					try {
						at.inverseTransformPoint(new Point2D.Double(e.getX(), e
								.getY()), userPoint);
					} catch (NoninvertibleTransformException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if(draggingModeStarting==false){
						// keep the reference point for dragging
						userStartDraggingPoint = userPoint;
						// this has to be done only once
						draggingModeStarting=true;
					}
					
					System.out.println("mouseDragged : " + e.getX() + " "
							+ e.getY() +  " User  : " + userPoint.x + " " + userPoint.y);
					
					// get the current bound
					Rectangle2D.Double bound = view.getCameraUserBound();
					
					// calculate the delta x and y
					double deltax = userPoint.x - userStartDraggingPoint.x;
					double deltay = userPoint.y - userStartDraggingPoint.y;
					
					// the user coordinate is the new center 
					// calculate the bottom left position of the visible bound
//					bound.setRect(
//							bound.x - deltax, 
//							bound.y - deltay, 
//							bound.width, // keep the same width anheight
//							bound.height);
					
					view.setCameraUserBound(
							bound.x - deltax,
							bound.y - deltay,
							bound.width
							);
					
				}
			}
		};
		return mml;
	}

	public double getScaleUnit() {
		return gvc.getScaleUnit();
	}

	public void setScaleUnit(double scaleUnit) {
		this.gvc.setScaleUnit(scaleUnit);
	}

	public double getScrollUnit() {
		return gvc.getScaleUnit();
	}

	public void setScrollUnit(double scrollUnit) {
		this.gvc.setScrollUnit(scrollUnit);
	}

}
