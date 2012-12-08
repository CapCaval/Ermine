package org.capcaval.ermine._test.customLayer;

import java.awt.Color;
import java.awt.Graphics2D;

import org.capcaval.ermine.mvc.view.layers._impl.j2d.J2DLayerImpl;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;

public class TestLayer extends J2DLayerImpl{
	int numberOfRender=0 ;
	
	@Override
	public void render(RenderInfo info, Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawString( this.getName() + "   render number : " + ++numberOfRender , 30, (layerNumber*30)+50);
	}
}
