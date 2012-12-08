package org.capcaval.ermine.mvc.view.layers._impl.j2d;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import org.capcaval.ermine.mvc.view.Renderer;
import org.capcaval.ermine.mvc.view.layers.shapelayer.ShapeLayerImpl;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;
import org.capcaval.ermine.mvc.view.shapes.OriginType;
import org.capcaval.ermine.mvc.view.shapes.Shape;



public class ShapeLayer2DImpl extends ShapeLayerImpl<Graphics2D> {

    @Override
    public void render(RenderInfo info, Graphics2D graphicContext)
    {
    	Graphics2D g = (Graphics2D) graphicContext.create();

    	g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		AffineTransform at = info.getViewTx().getAffineTransform();
		g.transform(at);
    	
    	
    	// draw all shapes
    	for(Shape shape : this.container.getShapeList()){
    		if(shape.getOriginType() == OriginType.USER){
    			if(shape.isVisible() == true){
    				// TODO apply the shape transformation if any
    				Renderer<Graphics2D> r = (Renderer<Graphics2D>)shape.getRenderer(); 
    				r.render(info, g);}
    		}
    		else if(shape.isVisible() == true){
    			Graphics2D gNoAT = (Graphics2D)g.create();
    			AffineTransform atNull = new AffineTransform();
    			gNoAT.setTransform(atNull);
    			
    			Renderer<Graphics2D> r = (Renderer<Graphics2D>)shape.getRenderer(); 
    			r.render(info, gNoAT);
    			gNoAT.dispose();
    		}
    	}
    	
		g.dispose();
    }

	@Override
	public Shape getShape(int id) {
		return this.container.getShape(id);
	}


	
}
