package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.Color;
import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.geom.RectangleShape;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.FillStyleImpl;

public class RectangleShapeImpl extends ShapesJ2DImpl implements
		RectangleShape, Renderer<Graphics2D> {

	private double height;
	private double width;
	private static int counter = 0;

	
	public RectangleShapeImpl(double x, double y, double width, double height, OriginType originType, FillStyle fillStyle, LineStyle lineStyle) {
		super("Rectangle#" + (counter++) , originType, x, y, fillStyle, lineStyle);
		this.height = height;
		this.width = width;
	}

	public RectangleShapeImpl(double x, double y, double width, double height) {
		super("Rectangle#" + (counter++) , OriginType.USER, x, y, new FillStyleImpl(Color.GRAY), new LineStyleJ2DImpl(Color.LIGHT_GRAY, 2));
		this.height = height;
		this.width = width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;

	}

	@Override
	public void setWidth(double width) {
		this.width = width;

	}

	@Override
	public boolean isDirty() {
		return true;
	}

	@Override
	public void setDirty(boolean isDirty) {
		// not used
	}

	@Override
	public void render(RenderInfo info, Graphics2D g) {
		// draw with style draw first the default style setting 
		super.render(info, g);
		
		// check out if the rec has to be filled if yes it has to be done first 
		// in order to not hide the line draw
		if(this.fillStyle != null){
			Graphics2D gFill = (Graphics2D)g.create();
			gFill.setPaint(this.fillStyle.getPainting());
			gFill.fillRect((int)this.x, (int)this.y, (int)this.width, (int)this.height);
		}
		
		// draw the specific class shape 
		g.drawRect((int)this.x, (int)this.y,(int)this.width, (int)this.height);
		
	}

}
