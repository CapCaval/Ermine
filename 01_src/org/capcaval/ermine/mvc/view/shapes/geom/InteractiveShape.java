package org.capcaval.ermine.mvc.view.shapes.geom;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import org.capcaval.awtextension.color.ColorUtil;


public class InteractiveShape {

	protected int x;
	protected int y;
	protected Double angleInDegree;
	protected boolean highlight = true;
	protected Shape bound;
	protected Shape shape;

	protected Color color;

	protected MouseMotionListener mouseMotionListener = null;

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}


	protected AffineTransform affineTransform;
	protected boolean isEnable;

	public InteractiveShape(int x, int y, Color c, Double rotationDegree) {
		this.x = x;
		this.y = y;
		this.angleInDegree = rotationDegree;
		this.color = c;

		this.setEnable(true);
	}

	public void setEnable(boolean enable) {
		this.isEnable = enable;
	}

	public AffineTransform getAffineTransform() {
		return affineTransform;
	}


	public java.awt.Shape getBound() {
		return this.bound;
	}

	public void render(Graphics2D g) {
		Graphics2D g2 = (Graphics2D) g.create();

		// compute and keep the latest transformation
		this.affineTransform = this.computeTransformations(g2);

		Graphics2D g3 = (Graphics2D) g2.create();
		this.renderShape(g3);
	}

	public AffineTransform computeTransformations(Graphics2D g) {
		if (this.angleInDegree != null) {
			g.rotate(-Math.toRadians(this.angleInDegree), x, y);
		}
		// move
		g.translate(x, y);

		// set the color
		g.setColor(this.color);

		return g.getTransform();
	}

	public void renderShape(Graphics2D g) {
		g.setStroke(new BasicStroke(2.0f));
		g.draw(shape);
	}

	public void renderSelection(Graphics2D g) {

		if (this.highlight == true) {
			Graphics2D g2 = (Graphics2D) g.create();
			//g2.setColor(Color.lighter(this.color, 0.5, 255));
			g2.setColor(this.color);
			g2.fill(this.shape);
		}

		// g.setStroke(new BasicStroke(2.0f));
		// g.draw(this.bound);
	}

	protected void renderPressed(Graphics2D g) {
		g.setColor(new Color(255, 255, 255, 150));
		g.fill(this.shape);
	}

	protected void renderMouseInside(Graphics2D g) {
		// g.setColor( new Color(
		// this.color.getRed(),
		// this.color.getGreen(),
		// this.color.getBlue(),
		// 150)); //alpha
		g.setColor(ColorUtil.lighter(this.color, 0.9, 125));

		g.fill(this.shape);
	}

	protected void renderDisable(Graphics2D g) {
		g.setColor(ColorUtil.lighter(this.color, 0.9, 100));
		g.fill(this.shape);
	}

}
