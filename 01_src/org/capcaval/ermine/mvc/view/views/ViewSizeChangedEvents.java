package org.capcaval.ermine.mvc.view.views;

import java.awt.geom.Rectangle2D;

public interface ViewSizeChangedEvents {

	public void notifyDeviceSizeChanged(Rectangle2D.Double deviceBound);
	public void notifyUserSizeChanged(Rectangle2D.Double deviceBound);
	
	public interface ViewSizeChangedSubscribe {
		public void subscribe(ViewSizeChangedEvents events);
		public void unSubscribe(ViewSizeChangedEvents events);
	}
	
}
