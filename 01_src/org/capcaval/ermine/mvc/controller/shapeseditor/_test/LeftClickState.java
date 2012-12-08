package org.capcaval.ermine.mvc.controller.shapeseditor._test;

import java.awt.event.MouseEvent;

import org.capcaval.ermine.mvc.view.shapes.Shape;

public interface LeftClickState extends State {

	void notifyMouseClicked(MouseEvent e, Shape shape);

}
