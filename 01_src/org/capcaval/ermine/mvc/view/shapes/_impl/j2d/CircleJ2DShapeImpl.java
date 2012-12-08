package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.geom.CircleShape;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;

public class CircleJ2DShapeImpl extends ShapesJ2DImpl implements CircleShape {

	private static int counter =0;
	private double radius;

	public CircleJ2DShapeImpl(double x, double y, OriginType originType, double radius, FillStyle fillStyle, LineStyle lineStyle) {
		super("Circle#" + counter++, originType, x, y, fillStyle, lineStyle);
		this.radius = radius;
	}

	public CircleJ2DShapeImpl() {
		super();
	}

	@Override
	public void render(RenderInfo info, Graphics2D g) {
		// draw with style draw first the default style setting
		super.render(info, g);

		Graphics2D gTemp = (Graphics2D)g.create();
		
		if( this.getFillStyle() != null){
			gTemp.setPaint(this.fillStyle.getPainting());
			gTemp.fillOval((int)this.x, (int)this.y, (int)this.radius, (int)this.radius);
		}
		gTemp.dispose();
		
		// draw the specific class shape
		g.drawOval((int)this.x, (int)this.y, (int)this.radius, (int)this.radius);


	}

	@Override
	public double getRadius() {
		return this.radius;
	}

	@Override
	public void setRadius(double radius) {
		this.radius = radius;
	}

}
