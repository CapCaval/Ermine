package org.capcaval.ermine.mvc.controller.shapeseditor._test;

import java.awt.event.MouseEvent;

import org.capcaval.ermine.mvc.view.shapes.Shape;

public class ShapesEditorTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		State[] stateList = new State[]{
				new LeftClickState(){
					@Override
					public
					void notifyMouseMoved(MouseEvent e, Shape shape){
						
					}

					@Override
					public void notifyMouseClicked(MouseEvent e, Shape shape) {
						// TODO Auto-generated method stub
						
					}
				}
		};
		
		
		// create a shapeEditor
		ShapesEditor se = new ShapesEditorImpl(stateList); 
		se.toString();

	}

}
