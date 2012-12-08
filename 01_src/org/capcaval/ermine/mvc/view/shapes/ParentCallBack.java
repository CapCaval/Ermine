package org.capcaval.ermine.mvc.view.shapes;

public interface ParentCallBack {

	void requestRepaint();

	void notifyRequestShapeOnTop(Shape shape);

	void notifyBoundChanged();

}
