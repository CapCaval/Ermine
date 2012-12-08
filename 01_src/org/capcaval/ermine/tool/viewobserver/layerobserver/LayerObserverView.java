package org.capcaval.ermine.tool.viewobserver.layerobserver;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class LayerObserverView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6300875854365511642L;
	protected JLabel layerName;
	protected JButton repaintBtn;
	protected JCheckBox isCached;
	protected JCheckBox isAsynchronous;
	
	public LayerObserverView(){
		this.init();
	}

	protected void init() {
		// create all the instance
		JLabel layerNamelb = new JLabel("Layer Name : ");
		this.layerName = new JLabel();
		this.repaintBtn = new JButton("Repaint ");
		JLabel isCachedlb = new JLabel(" cache : ");
		this.isCached = new JCheckBox();
		JLabel isAsynclb = new JLabel(" async : ");
		this.isAsynchronous = new JCheckBox();
		
		// put a box layout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// put a borber
		this.setBorder(new LineBorder(Color.BLACK));
		
		// compose all the component
		this.add(layerNamelb);
		this.add(this.layerName);
		this.add(this.repaintBtn);
		this.add(isCachedlb);
		this.add(this.isCached);
		this.add(isAsynclb);
		this.add(this.isAsynchronous);
		

	}

}
