package org.capcaval.ermine.mvc.model;

import org.capcaval.ermine.mvc.model._impl.ModelFactoryImpl;

public interface ModelFactory {

	public static ModelFactory factory = new ModelFactoryImpl();
	
	public <T>Model<T> newModel(Class<T> type);
	
}
