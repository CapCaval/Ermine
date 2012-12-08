package org.capcaval.ermine.mvc.view.layers._impl.j2d.aspects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;

public class AsyncCacheAspect implements Renderer<Graphics2D>, CacheSwapAccessor {
	protected BufferedImage workCache;
	public synchronized BufferedImage getWorkCache() {
		return workCache;
	}

	public synchronized void setWorkCache(BufferedImage workCache) {
		this.workCache = workCache;
	}

	public synchronized BufferedImage getAvailableCache() {
		return availableCache;
	}

	public synchronized void setAvailableCache(BufferedImage availableCache) {
		this.availableCache = availableCache;
	}

	public synchronized Layer getCachedLayer() {
		return cachedLayer;
	}

	public synchronized void setCachedLayer(Layer cachedLayer) {
		this.cachedLayer = cachedLayer;
	}

	protected BufferedImage availableCache;
	protected Timer timer;
	protected TimerTask asyncRender;
	protected AffineTransformation lastAT;
	protected Layer cachedLayer;
	protected boolean isDirty;
	protected Renderer<Graphics2D> originalRenderer;

	@Override
	public void render(RenderInfo info, Graphics2D g) {
		int width = (int) info.getDeviceBound().getWidth();
		int height = (int) info.getDeviceBound().getHeight();

		// create or recreate a buffer if it is new or if its size has changed
		if ((this.getWorkCache() == null) || (this.getWorkCache().getWidth() != width)
				|| (this.workCache.getHeight() != height)) {
			// create the buffer
			this.setWorkCache( new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB));
		}

		// repaint the cache if the affinetransform is not the same
		if ((this.lastAT == null)
				|| (this.isDirty == true)
				|| (this.lastAT.equals(info.getViewTx()) == false)) {

			this.launchAsyncPaintingInThread(info);
			
			// TODO add it inside the renderer
			this.lastAT = info.getViewTx();



		} else if(this.availableCache != null)  { // the affine transform is valide, paint it
			// keep a ref of the last
			// TODO add it inside the renderer
			this.lastAT = info.getViewTx();

			// draw the current available buffer
			g.drawImage(this.availableCache, 0, 0, null);
		}
	}

	private void launchAsyncPaintingInThread(final RenderInfo info) {
		// lazy creation of the runnable
		//if(this.asyncRender == null){
			this.asyncRender = new TimerTask() {
				
				@Override
				public void run() {
					launchAsyncPainting(info);
				}
			};
		//}
	
		
		// create a thread only the first time
		//if (this.timer == null)
			this.timer = new Timer("Async layer renderer " + cachedLayer.getName());
		
		
			
		// execute the render
		this.timer.schedule(this.asyncRender, (long)5000);
		
	}

	private void launchAsyncPainting(RenderInfo info) {
		int width = (int) info.getDeviceBound().getWidth();
		int height = (int) info.getDeviceBound().getHeight();
		
		// repaint the buffer with the new affine transformation
		Graphics2D cacheGraphics = this.workCache.createGraphics();

		// clear the graphic
		cacheGraphics.setBackground(new Color(0x44FF8C00, true));
		cacheGraphics.clearRect(0, 0, width, height);

		this.originalRenderer.render(info, cacheGraphics);
		// it is not dirty anymore it is cleaned
		this.isDirty = false;
		
		// swap the two buffers
		BufferedImage temp = this.availableCache;
		this.availableCache = this.workCache;
		this.workCache = temp;
		
		// request a repaint
		this.cachedLayer.repaint();
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
