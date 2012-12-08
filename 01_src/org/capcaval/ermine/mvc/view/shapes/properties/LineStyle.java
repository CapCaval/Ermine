package org.capcaval.ermine.mvc.view.shapes.properties;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

public interface LineStyle {
	public Color getColor();
	public void setColor(Color color);
	
	public Paint getPainting();
	public void setPainting(Paint painting);
	
	public Stroke getStroke();
	public void setStroke(Stroke stroke);
	
	public float getWidth();
	public void setWidth(float width);
}
