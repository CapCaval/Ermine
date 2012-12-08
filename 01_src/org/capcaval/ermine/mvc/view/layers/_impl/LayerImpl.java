package org.capcaval.ermine.mvc.view.layers._impl;

import java.awt.Component;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;

public class LayerImpl <G> implements Layer, Renderer<G> {

	static int globalLayerNumber = 0;
	
	protected int layerNumber;
	protected float opacity;
	protected boolean isVisible = true;
	protected PaintRequest paintCommand;
	protected String name;
	
	protected Renderer<?> renderer;

	private boolean isDirty;

	protected Component displayComponent;

	public LayerImpl(){
		this.layerNumber = LayerImpl.globalLayerNumber++;
		this.setName("Layer#" + layerNumber);
		
		//default renderer belongs is itself
		this.renderer = this;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public float getOpacity() {
		// TODO Auto-generated method stub
		return this.opacity;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.isVisible;
	}


	@Override
	public void render(RenderInfo info, G g) {
	}

	@Override
	public void setOpacity(float opacity) {
		this.opacity = opacity;
		
	}

	@Override
	public void setPaintCommand(PaintRequest command) {
		this.paintCommand = command;
		
	}

	@Override
	public void repaint() {
		this.paintCommand.requestPaint();
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
	public boolean isDirty() {
		boolean dirty = false;
		
		if(this.getRenderer() == this)
			dirty = this.isDirty;
		else
			dirty = this.getRenderer().isDirty();
		
		return dirty;
	}

	@Override
	public void setDirty(boolean isDirty) {
		if(this.getRenderer() == this)
			this.isDirty = isDirty;
		else
			this.getRenderer().setDirty(isDirty);
	}

	@Override
	public void setDisplayComponent(Component cmp) {
		this.displayComponent = cmp;
	}

}
