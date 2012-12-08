package org.capcaval.ermine.mvc.view.shapes.properties._impl;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;

public class LineStyleImpl implements LineStyle {

	protected Color color;
	protected float width;
	protected Stroke stroke;
	protected Paint painting;

	public LineStyleImpl(Color color, int width){
		this.color = color;
		this.width = width;
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public Paint getPainting() {
		return this.painting;
	}

	@Override
	public void setPainting(Paint painting) {
		this.painting = painting;
		
	}

	@Override
	public Stroke getStroke() {
		return this.stroke;
	}

	@Override
	public void setStroke(Stroke stroke) {
		this.stroke=stroke;
	}

}
