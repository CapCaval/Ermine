package org.capcaval.ermine.mvc.view.layers._impl.j2d.aspects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;

public class LayerCacheAspect implements Renderer<Graphics2D> {
	protected BufferedImage cache;
	protected Layer cachedLayer;
	protected boolean isDirty;
	protected Renderer<Graphics2D> originalRenderer;
	protected AffineTransformation lastViewTx;
	
	@Override
	public void render(RenderInfo info, Graphics2D g) {
		int width = (int) info.getDeviceBound().getWidth();
		int height = (int) info.getDeviceBound().getHeight();

		// create or recreate a buffer if it is new or if its size has changed
		if (	(this.cache == null) || 
				(this.cache.getWidth() != width) || 
				(this.cache.getHeight() != height)) {
			// create the buffer
			this.cache = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
		}

		// repaint the cache if the affinetransform is not the same
		if((this.lastViewTx == null)||
				(this.isDirty == true)||
				(this.lastViewTx.equals(info.getViewTx()) == false)){
			// repaint the buffer with the new affine transformation
			Graphics2D cacheGraphics = this.cache.createGraphics();
			
			// clear the graphic
			cacheGraphics.setBackground(new Color(0x44FF8C00, true));
			cacheGraphics.clearRect(0, 0, width, height);
			
			this.originalRenderer.render(info, cacheGraphics);
			// it is not dirty anymore it is cleaned
			this.isDirty = false;
		}
		
		// keep a ref of the last 
		// TODO add it inside the renderer
		this.lastViewTx = info.getViewTx();

		// draw the current buffer
		g.drawImage(this.cache, 0, 0, null);
	}

	@Override
	public boolean isDirty() {
		return this.isDirty;
	}

	@Override
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public void setOriginalRenderer(Renderer<Graphics2D> originalRenderer) {
		this.originalRenderer = originalRenderer;
	}

	public Renderer<Graphics2D> getOriginalRenderer() {
		return this.originalRenderer;
	}

}
