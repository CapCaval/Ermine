package org.capcaval.ermine.mvc.view.painter;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;

public class RenderInfo {
	
	protected Rectangle deviceBound;
	private Double userBound;
	protected AffineTransformation viewTx;
	private double deltaXInPixel;
	private double deltaYInPixel;

	public RenderInfo(AffineTransformation viewTx, Rectangle deviceBound, Rectangle2D.Double userBound){
		this.viewTx = viewTx;
		this.deviceBound = deviceBound;
		this.userBound = userBound;
	}

	public Rectangle getDeviceBound() {
		return this.deviceBound;
	}

	public Double getUserBound() {
		// TODO Auto-generated method stub
		return this.userBound;
	}

	public AffineTransformation getViewTx() {
		return this.viewTx;
	}

	public double getViewDeltaXPixel() {
		return this.deltaXInPixel;
	}
	public double getViewDeltaYPixel() {
		return this.deltaYInPixel;
	}

}
