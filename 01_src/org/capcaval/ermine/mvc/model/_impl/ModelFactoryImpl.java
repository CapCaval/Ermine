package org.capcaval.ermine.mvc.model._impl;

import org.capcaval.ermine.mvc.model.Model;
import org.capcaval.ermine.mvc.model.ModelFactory;


public class ModelFactoryImpl implements ModelFactory {

	public <T>Model<T> newModel(Class<T> type) {
		return new ModelImpl<T>();
	}

}
