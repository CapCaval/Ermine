package org.capcaval.ermine.mvc.controller.shapeseditor._test;

import java.awt.event.MouseEvent;

import org.capcaval.ermine.mvc.view.shapes.Shape;

public interface State {

	void notifyMouseMoved(MouseEvent e, Shape shape);

}
