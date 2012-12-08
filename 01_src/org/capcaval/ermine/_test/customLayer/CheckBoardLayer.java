package org.capcaval.ermine._test.customLayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.capcaval.ermine.mvc.view.layers._impl.j2d.J2DLayerImpl;
import org.capcaval.ermine.mvc.view.painter.RenderInfo;

public class CheckBoardLayer extends J2DLayerImpl{
	int numberOfRender=0 ;
	
	public CheckBoardLayer(){
		this.setName("Check Board");
	}
	
	@Override
	public void render(RenderInfo info, Graphics2D gOrigin) {
		
		System.out.println("checker render");
		
		Graphics2D g = (Graphics2D)gOrigin.create();
		AffineTransform at = info.getViewTx().getAffineTransform();
		

		
//		double scalex = at.getScaleX();
//		double scaley = at.getScaleY();
		//at.setToScale(scalex, scaley);
		g.transform(at);
		
		g.setColor(Color.LIGHT_GRAY);

		for (int i = 0; i < 1000; i = i + 100) {
			for (int j = 0; j < 1000; j = j + 100) {
				g.fillRect(i, j, 50, 50);
			}
		}
		

		for (int i = 50; i < 1000; i = i + 100) {
			for (int j = 50; j < 1000; j = j + 100) {
				g.fillRect(i, j, 50, 50);
			}
		}

		g.setColor(Color.CYAN);

		Integer index = 1;
		
		
		
		for (int i = 0; i < 1000; i = i + 50) {
			g.drawString((index++).toString(), 150 + 10, i + 25); // set position
		}
		

		Graphics2D g2 = (Graphics2D)g.create();
		g2.setColor(Color.DARK_GRAY);
		g2.drawRect(0, 0, 10, 10);
		g2.drawRect(15, 0, 10, 10);
		g2.setColor(Color.RED);
		g2.drawRect(0, 15, 10, 10);

		Graphics2D g3 = (Graphics2D)gOrigin.create();
		AffineTransform at2  = new AffineTransform();
//		AffineTransform at2  = 
//			AffineTransform.getTranslateInstance(at.getTranslateX(), at.getTranslateY());
		//at2.setToTranslation(30d, 50d);
		at2.setToScale(1, -1);
		at2.concatenate(at);
		
		g3.setTransform(at2);
		//g3.setToScale(1, -1);
		
		g3.setColor(Color.BLACK);
		g3.drawString( "####" + this.getName() + " ##   render number : " + ++numberOfRender , 30, 50);
		g3.setTransform(at);

		Graphics2D gtest = (Graphics2D)gOrigin.create();
		gtest.setColor(Color.BLUE);
//		gtest.translate(10, 400);
//		gtest.scale(1, -1);
//		gtest.translate(-10, -400);
		gtest.drawString("Test1 +++++++++++++++ " + numberOfRender, 10, 400);
		gtest.setTransform(at);
		gtest.translate(50, 400);
		gtest.scale(1, -1);
		gtest.translate(-50, -400);
		
		gtest.drawString("Test2 +++++++++++++++ " + numberOfRender, 50, 400);
		

		
	}
}
