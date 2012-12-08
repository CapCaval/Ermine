package org.capcaval.ermine.tool.viewobserver;

import javax.swing.JPanel;

import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.aspects.LayerAspectInjector;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.tool.viewobserver.layerobserver.LayerObserverController;


public class ViewObserverController {

	protected ViewObserverView view;
	protected LayerAspectInjector lai;
	
	public ViewObserverController(View ObserveView){
		this.init(ObserveView);
	}
	
	protected void init(View observeView) {
		// allocate the aggregate
		this.view = new ViewObserverView();
		
		// create aLayerAspectInjector
		this.lai = new LayerAspectInjector();
		
		// 
		for(Layer layer : observeView.getLayerList()){
			// allocate a new layer gadget
			LayerObserverController layerCtrl  = new LayerObserverController(layer, lai);
			this.view.add(layerCtrl.getPanel());
		}
	}

	public JPanel getPanel(){
		return this.view;
	}
	
}
