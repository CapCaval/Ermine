package org.capcaval.ermine.mvc.view.views;


import java.awt.Component;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.capcaval.ermine.mvc.view.layers.Layer;
import org.capcaval.ermine.mvc.view.transformation.AffineTransformation;
import org.capcaval.ermine.mvc.view.views.ViewSizeChangedEvents.ViewSizeChangedSubscribe;

public interface View{

	public String getName();
	public void setName(String name);

	
	public List<Layer> getLayerList();
	public void addLayer(Layer layer);
	public void insertLayer(int index, Layer layer);
	public void removeLayer(int id);
	
	//public void composeAllLayer(Object drawable);	
	
	public void setCameraUserBound( Rectangle2D.Double bound);
	public Rectangle2D.Double getCameraUserBound();

	public double getTopLeftX();
	public double getTopLeftY();
	public double getUserWidth();

	//TODO a revoir mettre la notion de border
	Rectangle getDeviceBound();
	void setDeviceBound(Rectangle bound);
	public void setDisplayComponent(Component contentPane);
	public Component getDisplayComponent();
	public ViewSizeChangedSubscribe getViewSizeChangedSubscribe();
	public AffineTransformation getAffineTransformation();
	public void setCameraUserBound(double topLeftX, double topLeftY, double Width);
}
