package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;

public class PolylineJ2DShapeImpl extends ShapesJ2DImpl {

	private static int counter =0;

	public PolylineJ2DShapeImpl(int x, int y, OriginType originType, FillStyle fillStyle, LineStyle lineStyle) {
		super("Polyline#" + counter++, originType, x, y, fillStyle, lineStyle);
	}

	@Override
	public void render(RenderInfo info, Graphics2D g) {
		// draw with style draw first the default style setting
		super.render(info, g);

		//
		int[] xpoints = new int[] {150, 150, 250};
		int[] ypoints = new int[] {150, 100, 100};
		
		// draw the specific class shape
		g.drawPolyline(xpoints, ypoints, xpoints.length);

	}

}
