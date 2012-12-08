package org.capcaval.ermine.mvc.view.layers._impl.j2d;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.capcaval.ermine.mvc.view.layers.GridLayer;
import org.capcaval.ermine.mvc.view.layers._impl.LayerImpl;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;


public class GridLayer2DImpl extends LayerImpl<Graphics2D> implements GridLayer{

    private double gridSize;

	public GridLayer2DImpl(double gridSize) {
		this.gridSize = gridSize;
	}

	public GridLayer2DImpl() {
		this.gridSize = 50; // default size
	}

	@Override
    public void render(RenderInfo info, Graphics2D graphicContext)
    {
    	Graphics2D g = (Graphics2D) graphicContext.create();
    	g.setColor(new Color(40, 40, 40, 125));
    	
    	g.setFont(new Font("sansserif", Font.PLAIN, 7));

    	g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    	Rectangle2D.Double userBound = info.getUserBound();
    	
    	// get all the grid values
    	Double[] horizontalValues = computeRangeValues(
    			userBound.y-userBound.height, userBound.y, this.gridSize
    	);
    	
    	// draw all the horizontal lines
    	this.drawHorizontalLines(info, g, horizontalValues);

    	Double[] verticalValues = computeRangeValues(
    			userBound.x, userBound.x+userBound.width, this.gridSize
    	);

    	// draw all the vertical lines
    	this.drawVerticalLines(info, g, verticalValues);
    	
    	
    	
		g.dispose();
    }

	private void drawVerticalLines(RenderInfo info, Graphics2D g, Double[] verticalValues) {
		int ymax = info.getDeviceBound().height;
 
		AffineTransform at = info.getViewTx().getAffineTransform();
		Point2D.Double in = new Point2D.Double();
		Point2D.Double out = new Point2D.Double();
		
		Color currentColor = null;
		
		for(Double xUser : verticalValues){
			in.setLocation(xUser, 0);
			// convert in device
			at.transform(in, out);
			
			if(xUser == 0){
				currentColor = g.getColor();
				g.setColor(Color.RED);
				}
			
			g.drawLine((int)out.x, 0, (int)out.x, ymax);
			g.drawString(Double.toString(xUser), (int)out.x, 10);
			
			if(xUser == 0){
				g.setColor(currentColor);
			}
		}
		
	}

	private void drawHorizontalLines(RenderInfo info, Graphics2D g, Double[] horizontalValues) {
		int xmax = info.getDeviceBound().width;
		 
		AffineTransform at = info.getViewTx().getAffineTransform();
		Point2D.Double in = new Point2D.Double();
		Point2D.Double out = new Point2D.Double();
		
		Color currentColor =null;
		
		for(Double yUser : horizontalValues){
			in.setLocation(0, yUser);
			// convert in device
			at.transform(in, out);
			
			if(yUser == 0){
				currentColor = g.getColor();
				g.setColor(Color.RED);
			}
			g.drawLine(0, (int)out.getY(), xmax, (int)out.getY());
			g.drawString(Double.toString(yUser), 5, (int)out.getY());
			if(yUser == 0){
				g.setColor(currentColor);
			}
			
		}
		
	}

	public static Double[] computeRangeValues(double minValue, double maxValue, double gap) {
		
		boolean over = false;
		
		double currentValue = minValue;
		
		List<Double> valueList = new ArrayList<Double>();
		
		while (over == false) {

			double quotient = currentValue / gap;
			int remainder = (int) (currentValue - ((int)quotient * gap));

			if (currentValue > maxValue) {
				over = true;
			} else {
				if (remainder == 0)
					// keep the value
					valueList.add(currentValue);
			}
			// next value
			if((currentValue < 0)&&(valueList.size() == 0))
				currentValue = ((int)quotient) * gap;
			else
				currentValue = ((int)quotient+1) * gap;
		}
		
		return valueList.toArray(new Double[0]);
	}

	@Override
	public double getGridSizeInUser() {
		return this.gridSize;
	}

	@Override
	public void setGridSizeInUser(double size) {
		this.gridSize = size;
	}
}
