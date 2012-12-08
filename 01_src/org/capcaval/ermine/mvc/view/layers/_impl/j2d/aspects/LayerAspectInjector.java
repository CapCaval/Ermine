package org.capcaval.ermine.mvc.view.layers._impl.j2d.aspects;

import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers.Layer;

public class LayerAspectInjector {
	@SuppressWarnings("unchecked")
	public void addCacheRenderToLayer(Layer layer){
		// allocate a cache Renderer
		LayerCacheAspect lca = new LayerCacheAspect();
		
		// retrieve the original renderer
		Renderer<Graphics2D> originalRenderer = (Renderer<Graphics2D>)layer.getRenderer();
		
		// keep a ref of the previous renderer
		lca.setOriginalRenderer(originalRenderer);
		
		// set the the new cached renderer
		layer.setRenderer(lca);
	}

	public void removeCacheRenderToLayer(Layer layer){
		// retrieve the LayerCacheAspect
		LayerCacheAspect lca = (LayerCacheAspect)layer.getRenderer();
			
		// get the original renderer
		Renderer<Graphics2D> originalRenderer = lca.getOriginalRenderer();
		
		// set it back
		layer.setRenderer(originalRenderer);
	}
	
	public void addAsyncRenderToLayer(Layer layer){
		// allocate a cache Renderer
		AsyncCacheAspect aca = new AsyncCacheAspect();
		
		// retrieve the original renderer
		Renderer<Graphics2D> originalRenderer = (Renderer<Graphics2D>)layer.getRenderer();
		
		// keep a ref of the previous renderer
		aca.setOriginalRenderer(originalRenderer);
		aca.setCachedLayer(layer);
		
		// set the the new cached renderer
		layer.setRenderer(aca);
	}
	public void removeAsyncRenderToLayer(Layer layer){
		// retrieve the LayerCacheAspect
		AsyncCacheAspect aca = (AsyncCacheAspect)layer.getRenderer();
			
		// get the original renderer
		Renderer<Graphics2D> originalRenderer = aca.getOriginalRenderer();
		
		// set it back
		layer.setRenderer(originalRenderer);
	}

}
