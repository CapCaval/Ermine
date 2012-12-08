package org.capcaval.ermine.mvc.view.shapes.geom;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


public class DeviceCompositeShape extends InteractiveShape{

	protected List<InteractiveShape> deviceShapelist = new ArrayList<InteractiveShape>();

	public DeviceCompositeShape(int x, int y, Color c, Double rotationDegree) {
		super(x, y, c, rotationDegree);
	}

	public void addShape(InteractiveShape deviceShape){
		this.deviceShapelist.add(deviceShape);
	}
	
	@Override
	public void renderShape(Graphics2D g){
		// draw the children
		for(InteractiveShape shape : this.deviceShapelist){
			shape.render(g);
		}
	}
}
