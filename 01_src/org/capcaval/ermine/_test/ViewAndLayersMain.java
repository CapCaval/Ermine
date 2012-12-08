package org.capcaval.ermine._test;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

import org.capcaval.ermine._test.customLayer.CheckBoardLayer;
import org.capcaval.ermine._test.customLayer.TestLayer;
import org.capcaval.ermine.application.ErmineFrame;
import org.capcaval.ermine.mvc.controller.MouseViewController;
import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views._impl.j2d.J2DViewImpl;
import org.capcaval.ermine.tool.viewobserver.ViewObserverController;

public class ViewAndLayersMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ErmineFrame frame = new ErmineFrame("Java2D test", new Rectangle(0, 0,
				500, 600));
		View view = new J2DViewImpl();

		
		frame.addView(view);

		// add a checker layer
		CheckBoardLayer cbLayer = new CheckBoardLayer();
		view.addLayer(cbLayer);
		
		for (int i = 0; i < 5; i++) {
			TestLayer layer = new TestLayer();
			view.addLayer(layer);
		}
		
		// add a mouse controller on the view
		new MouseViewController(view);

		//frame.setVisible(true);
		
		// define where to look at
		view.setCameraUserBound(new Rectangle2D.Double(-100, -100, 1500, 1500));
		
		frame.setVisible(true);
		

		
		// 
		JFrame obsFrame = new JFrame("Observer");
		obsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obsFrame.setBounds(500, 0, 500, 600);
		
		// create an observer
		ViewObserverController voc = new ViewObserverController(view);
		obsFrame.getContentPane().add(voc.getPanel());
		
		obsFrame.setVisible(true);
	}

}
