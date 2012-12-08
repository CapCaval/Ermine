package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Area;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;

public class AwtShapeShapeImpl extends ShapesJ2DImpl implements
		Shape, Renderer<Graphics2D> {

	private static int counter = 0;
	
    protected java.awt.Shape aWTShape;

	
	public AwtShapeShapeImpl(double x, double y, java.awt.Shape shape, OriginType originType, FillStyle fillStyle, LineStyle lineStyle) {
		super("AwtShape#" + (counter++) , originType, x, y, fillStyle, lineStyle);
		this.aWTShape = shape;
		this.bounds=new Area(shape);
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
		// draw the associated shape
		if (AwtShapeShapeImpl.this.aWTShape != null){
			// backup context.
			Stroke strokeBackup = g.getStroke();
			java.awt.Paint paintBackup = g.getPaint();
			
			// paint the filling if any
			if( AwtShapeShapeImpl.this.fillStyle != null){
				if(AwtShapeShapeImpl.this.fillStyle.getPainting() != null){
					g.setPaint(AwtShapeShapeImpl.this.fillStyle.getPainting());
				}
				g.fill(AwtShapeShapeImpl.this.aWTShape);
			}

			// paint the line if any
			if( AwtShapeShapeImpl.this.lineStyle != null){
				if(AwtShapeShapeImpl.this.lineStyle.getPainting() != null){
					g.setPaint( AwtShapeShapeImpl.this.lineStyle.getPainting());
				}
				if(AwtShapeShapeImpl.this.lineStyle.getStroke() != null){
					g.setStroke(AwtShapeShapeImpl.this.lineStyle.getStroke());
				}
				
				g.draw(AwtShapeShapeImpl.this.aWTShape);
			}
			// save the used affine transformation
			AwtShapeShapeImpl.this.renderedTx = g.getTransform();
			
			// restore context.
			g.setStroke(strokeBackup);
			g.setPaint(paintBackup);
		}   
	}

}
