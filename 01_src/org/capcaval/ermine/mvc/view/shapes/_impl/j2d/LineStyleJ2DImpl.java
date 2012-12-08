package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.LineStyleImpl;

public class LineStyleJ2DImpl extends LineStyleImpl implements LineStyle, Renderer<Graphics2D> {
	
	protected BasicStroke bs;

	public LineStyleJ2DImpl(Color color, int width) {
		super(color, width);
		this.bs =  new BasicStroke(this.width);
	}

	@Override
	public void render(RenderInfo info, Graphics2D g) {
		g.setStroke(bs); 
		g.setColor(this.color);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDirty(boolean isDirty) {
		// TODO Auto-generated method stub
		
	}
}
