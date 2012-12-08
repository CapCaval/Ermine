package org.capcaval.ermine.mvc.view.shapes._impl.j2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.Transformer;
import org.capcaval.ermine.mvc.view.shapes._impl.ShapeImpl;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.object.CopyTo;

public class ShapesJ2DImpl extends ShapeImpl<Graphics2D> implements CopyTo{

	BasicStroke bs =null;

	static LineStyle defaultLineStyle = new LineStyleJ2DImpl(Color.DARK_GRAY, 1);
	static BasicStroke defaultStroke = new BasicStroke(defaultLineStyle.getWidth());
	
	public ShapesJ2DImpl(){}
		
	
	public ShapesJ2DImpl(String name, OriginType originType, double x, double y,
			FillStyle fillStyle, LineStyle lineStyle){
	
		super(
				name, 
				originType, 
				x, y, 
				fillStyle, 
				lineStyle==null?ShapesJ2DImpl.defaultLineStyle:lineStyle);
	}
	
	@Override
	public void render(RenderInfo info, Graphics2D g) {
		if(this.lineStyle != null){
			g.setStroke(defaultStroke);
			g.setColor(this.lineStyle.getColor());}

	}


	@Override
	public ShapeEventHandler getShapeEventHandler() {
		return this.shapeEventHandler;
	}


	@Override
	public void setEventHandler(ShapeEventHandler eventHandler) {
		this.shapeEventHandler  = eventHandler;
		
	}
	@Override
	public void copyTo(Object obj){
		super.copyTo(obj);
		ShapesJ2DImpl copy = (ShapesJ2DImpl)obj;
		copy.bs = new BasicStroke(
				this.bs.getLineWidth(),
				this.bs.getEndCap(),
				this.bs.getLineJoin(),
				this.bs.getMiterLimit(),
				this.bs.getDashArray(),
				this.bs.getDashPhase());
	}
}
