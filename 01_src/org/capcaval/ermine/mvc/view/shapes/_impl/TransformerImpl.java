package org.capcaval.ermine.mvc.view.shapes._impl;

import java.awt.geom.AffineTransform;

import org.capcaval.ermine.mvc.view.shapes.Transformer;

public class TransformerImpl implements Transformer {

	private AffineTransform affineTransform;

	public TransformerImpl(){
		this.affineTransform = new AffineTransform();
	}
	
	@Override
	public AffineTransform getAffineTransform() {
		return this.affineTransform;
	}

}
