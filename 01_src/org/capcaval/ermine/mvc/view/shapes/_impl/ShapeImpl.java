package org.capcaval.ermine.mvc.view.shapes._impl;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.CursorPosition;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.ParentCallBack;
import org.capcaval.ermine.mvc.view.shapes.Shape;
import org.capcaval.ermine.mvc.view.shapes.ShapeSubscriber;
import org.capcaval.ermine.mvc.view.shapes.Transformer;
import org.capcaval.ermine.mvc.view.shapes.event.Selection;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeDragAndDropEvent;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeEventHandler;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeInAndOutEvent;
import org.capcaval.ermine.mvc.view.shapes.event.ShapeMouseClickEvent;
import org.capcaval.ermine.mvc.view.shapes.event._impl.ShapeEventHandlerImpl;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.ClickEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.DragEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.event.eventwrapper.MoveEventWrapper;
import org.capcaval.ermine.mvc.view.shapes.properties.FillStyle;
import org.capcaval.ermine.mvc.view.shapes.properties.LineStyle;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.FillStyleImpl;
import org.capcaval.ermine.mvc.view.shapes.properties._impl.LineStyleImpl;
import org.capcaval.object.CopyTo;

public class ShapeImpl <G>implements Renderer<G>, Shape, ShapeSubscriber, CopyTo {

	protected	String name;
	protected	OriginType originType;
	protected	double x;
	protected	double y;
	protected	FillStyle fillStyle;
	protected	LineStyle lineStyle;
	protected Renderer<?> renderer;
	protected boolean enable;
	protected int id;
	protected Area bounds = new Area();
	protected boolean isActivate;
	protected boolean isVisible;
	protected Transformer transformer;
//	protected RenderType renderType;
	protected ParentCallBack callBackForChildren;
	protected AffineTransform renderedTx = new AffineTransform();
	protected ShapeEventHandler shapeEventHandler;
	protected Selection selection;
	protected CursorPosition cursorPosition;
	

	public ShapeImpl() {
		this.init();
	}

	private void init() {
		// set a default mouse Shape Handler it is used only in the case that
		// events are set
		// before that the shape is added in a container or a layer
		this.shapeEventHandler = new ShapeEventHandlerImpl();
		
		// the default state are not selected and outside
		this.selection = Selection.SHAPE_NOT_SELECTED;
		this.cursorPosition = CursorPosition.OUTSIDE_SHAPE;
		
		this.transformer = new TransformerImpl();
		
		this.isVisible = true;
		
		this.setRenderer(this);
	}

	public ShapeImpl(String name, OriginType originType, double x, double y,
			FillStyle fillStyle, LineStyle lineStyle) {

		this.init();
		
		this.name=name;
		this.originType=originType;
		this.x=x;
		this.y=y;
		
		if(fillStyle == null)
			fillStyle = new FillStyleImpl(Color.GRAY);

		if(lineStyle == null)
			lineStyle = new LineStyleImpl(Color.WHITE, 2);
		
		this.fillStyle=fillStyle;
		this.lineStyle=lineStyle;
		
		this.isVisible = true;
		

		

	}

	@Override
	public FillStyle getFillStyle() {
		return fillStyle;
	}

	@Override
	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public OriginType getOriginType() {
		return this.originType;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public void setFillStyle(FillStyle fillStyle) {
		this.fillStyle = fillStyle;

	}

	@Override
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setOriginType(OriginType originType) {
		this.originType = originType;
	}

	@Override
	public Renderer<?> getRenderer() {
		return this.renderer;
	}

	@Override
	public void setRenderer(Renderer<?> renderer) {
		this.renderer = renderer;

	}

	@Override
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;

	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void setDirty(boolean isDirty) {
		// do nothing
	}

	@Override
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public Area getBoundInPixel() {
		Area boundInPixel = (Area)this.bounds.clone();
		boundInPixel.transform(this.renderedTx);
		
		return boundInPixel;
	}

	@Override
	public void render(RenderInfo info, G g) {
	
	}

	@Override
	public ShapeEventHandler getShapeEventHandler() {
		return this.shapeEventHandler;
	}

	@Override
	public Transformer getTransformer() {
		return this.transformer;
	}

	@Override
	public boolean isActivate() {
		return isActivate;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setActivate(boolean activate) {
		this.isActivate  = activate;
		
	}

	@Override
	public void setEventHandler(ShapeEventHandler eventHandler) {
		this.shapeEventHandler = eventHandler;
	}

	@Override
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	@Override
	public void setParentCallBack(ParentCallBack callBackForChildren) {
		this.callBackForChildren = callBackForChildren;
	}

	@Override
	public AffineTransform getRenderedTx() {
		return this.renderedTx;
	}
	
	@Override
	public String toString(){
		return this.hashCode() + " " + this.name + " x:"+this.x + " y:"+this.y 
		+ " isVisible:" +this.isVisible; 
	}

	@Override
	public ShapeSubscriber getShapeSubscriber() {
		return this;
	}

	@Override
	public void addShapeDragAndDropEventObserver(ShapeDragAndDropEvent observer) {
    	// wrap the observer
    	DragEventWrapper obsWrapped = new DragEventWrapper(this, observer, null); // only containers have technical event so put it at null
    	
    	// add the wrap event to the handler
    	this.shapeEventHandler.addShapeDragAndDropEventObserver(obsWrapped);
	}

	@Override
	public void addShapeInAndOutEventObserver(ShapeInAndOutEvent observer) {
    	// wrap the observer
    	MoveEventWrapper obsWrapped = new MoveEventWrapper(this, observer, null); // only containers have technical event so put it at null
    	
    	// add the wrap event to the handler
    	this.shapeEventHandler.addShapeInAndOutEventObserver(obsWrapped);
	}

	@Override
	public void addShapeMouseClickEventObserver(ShapeMouseClickEvent observer) {
    	// wrap the observer
    	ClickEventWrapper obsWrapped = new ClickEventWrapper(this, observer); // only containers have technical event so put it at null
    	
    	// add the wrap event to the handler
    	this.shapeEventHandler.addShapeMouseClickEventObserver(obsWrapped);
	}

	@Override
	public void removeShapeDragAndDropEventObserver(
			ShapeDragAndDropEvent observer) {
		// TODO
	}

	@Override
	public void removeShapeInAndOutEventObserver(ShapeInAndOutEvent observer) {
		// TODO 
		
	}

	@Override
	public void removeShapeMouseClickEventObserver(ShapeMouseClickEvent observer) {
		// TODO 
		
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return this.selection;
	}

	@Override
	public void setSelection(Selection selection) {
		this.selection = selection;
		
	}

	@Override
	public CursorPosition getCursorPosition() {
		return this.cursorPosition;
	}

	@Override
	public void setCursorPosition(CursorPosition cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
	
	@Override
	public void copyTo(Object obj){
		ShapeImpl copy = (ShapeImpl)obj;
		copy.name = this.name;
		copy.bounds = (Area)this.bounds.clone();
		copy.enable = this.enable;
		copy.fillStyle.setPainting(this.fillStyle.getPainting());
		copy.lineStyle.setColor(this.lineStyle.getColor());
		copy.lineStyle.setWidth(this.lineStyle.getWidth());
		copy.x = this.x;
		copy.y = this.y;
		copy.originType = this.originType;
	}

	@Override
	public void setId(int id) {
		this.id=id;
		
	}
}
