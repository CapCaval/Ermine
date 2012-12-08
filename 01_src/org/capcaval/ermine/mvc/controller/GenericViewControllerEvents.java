package org.capcaval.ermine.mvc.controller;

public interface GenericViewControllerEvents {
	
	public void notifyScaleChanged(float changedScale);

	
	public interface GenericViewControllerEventsSubscribe {
		
		public void subscribe(GenericViewControllerEvents observer);
		public void unsubscribe(GenericViewControllerEvents observer);

	}

}


