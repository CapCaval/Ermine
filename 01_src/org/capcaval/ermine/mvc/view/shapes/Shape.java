package org.capcaval.ermine.mvc.view.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.shapes.event.Selection;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.object.CopyTo;

public interface Shape extends CopyTo{
	public int getId();
	public void setId(int id);
	
	public String getName();
	public void setName(String name);

	public OriginType getOriginType();
	public void setOriginType(OriginType originType);
	
	public Renderer<?> getRenderer();
	public void setRenderer(Renderer<?> renderer);
	public void setPosition(double i, double j);
	
	public double getX();
	public double getY();
	
	public LineStyle getLineStyle();
	public void setLineStyle(LineStyle lineStyle);
	
	public FillStyle getFillStyle();
	public void setFillStyle(FillStyle fillStyle);
	
	// transformer
	Transformer getTransformer();
	
	// eventHandler
	ShapeEventHandler getShapeEventHandler();
	void setEventHandler(ShapeEventHandler eventHandler);
	
	// activate
	public boolean isActivate();
	public void setActivate(boolean activate);
	
	// visible
	public boolean isVisible();
	public void setVisible(boolean visible);

	public Area getBoundInPixel();
	public void setEnable(boolean enable);

	public void setParentCallBack(ParentCallBack callBackForChildren);
	public AffineTransform getRenderedTx();
	ShapeSubscriber getShapeSubscriber();
	public Selection getSelection();
	public void setSelection(Selection selection);

	public CursorPosition getCursorPosition();
	public void setCursorPosition(CursorPosition cursorPosition);

}
