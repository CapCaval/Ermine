package org.capcaval.ermine.mvc.view.shapes.properties._impl;

import java.awt.Paint;

import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;

public class FillStyleImpl implements FillStyle {

	private Paint paint;

	public FillStyleImpl(Paint paint) {
		this.paint = paint;
	}

	@Override
	public Paint getPainting() {
		return paint;
	}

	@Override
	public void setPainting(Paint paint) {
		this.paint=paint;
		
	}

}
