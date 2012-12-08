package org.capcaval.ermine.mvc.view.views._impl.j2d;


import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.capcaval.ermine.mvc.view.views.View;
import org.capcaval.ermine.mvc.view.views._impl.ViewImpl;

public class J2DViewImpl extends ViewImpl<Graphics2D> implements View {

	class DisplayComponent extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			composeAllLayer((Graphics2D)g);
		}
	}
	@Override
	protected void init() {
		super.init();
		// allocate the display component
		this.setDisplayComponent(new DisplayComponent());

	}
	
}
