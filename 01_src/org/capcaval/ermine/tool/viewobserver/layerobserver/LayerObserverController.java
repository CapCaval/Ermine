package org.capcaval.ermine.tool.viewobserver.layerobserver;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.layers._impl.j2d.aspects.LayerAspectInjector;

public class LayerObserverController {
	
	protected LayerObserverView view;
	Layer layer;
	private LayerAspectInjector lai;
	
	public LayerObserverController(Layer layer, LayerAspectInjector lai){
		this.init(layer, lai);
	}
	
	protected void init(Layer obsLayer, LayerAspectInjector layerAspectInjector) {
		this.lai = layerAspectInjector;
		
		this.layer = obsLayer;
		// allocate the aggregate
		this.view = new LayerObserverView();
		
		//set the value
		this.view.layerName.setText(this.layer.getName());
		
		// create the 
		this.view.repaintBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// set it dirty
				layer.getRenderer().setDirty(true);
				layer.repaint();
			}
		});
		
		this.view.isCached.addItemListener( new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED){
							lai.addCacheRenderToLayer(layer);
						}
						else{
							lai.removeCacheRenderToLayer(layer);
						}
					}});
		this.view.isAsynchronous.addItemListener( new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					lai.addAsyncRenderToLayer(layer);
				}
				else{
					lai.removeAsyncRenderToLayer(layer);
				}
			}});

	}

	public JPanel getPanel(){
		return this.view;
	}

}
