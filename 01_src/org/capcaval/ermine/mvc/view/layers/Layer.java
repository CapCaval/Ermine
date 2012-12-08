package org.capcaval.ermine.mvc.view.layers;

import java.awt.Component;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers._impl.PaintRequest;

public interface Layer{

	public Renderer<?> getRenderer();
	public void setRenderer(Renderer<?> renderer);
	
	public float getOpacity();
	public void setOpacity(float opacity);
	
	public boolean isVisible();	
	
	public void setPaintCommand(PaintRequest command);
	public String getName();
	public void setName(String name);
	
	public void repaint();
	
	public void setDisplayComponent(final Component cmp);
}
