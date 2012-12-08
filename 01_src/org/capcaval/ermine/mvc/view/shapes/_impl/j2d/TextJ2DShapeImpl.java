package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;

public class TextJ2DShapeImpl extends ShapesJ2DImpl {

	private String text;

	public TextJ2DShapeImpl(double x, double y, OriginType origin, String text) {
		super("Text",origin, x, y, null, null);
		this.text = text;
	}

	@Override
	public void render(RenderInfo info, Graphics2D g) {
		// draw with style draw first the default style setting
		super.render(info, g);

		//TODO delete
		// remove the mirror display
		if (this.originType == OriginType.USER) {
			g.translate(this.getX(), this.getY());
			g.scale(1, -1);
			// go back
			g.translate(-this.getX(), -this.getY());
		}

		// draw the specific class shape
		g.drawString(this.text, (int)this.x, (int)this.y);

	}

}
