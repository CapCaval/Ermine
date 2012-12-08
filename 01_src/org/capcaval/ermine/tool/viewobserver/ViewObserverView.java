package org.capcaval.ermine.tool.viewobserver;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ViewObserverView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8141720266872966757L;

	public ViewObserverView(){
		this.init();
	}

	protected void init() {
		// set the layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	
	
}
