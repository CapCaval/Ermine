package org.capcaval.ermine.mvc.model;


public class Data<T> {

	protected int id;
	protected T object;
	
	public Data( final int id, final T object){
		this.id = id;
		this.object = object;
	}

	public int getId() {
		return id;
	}

	public T getObject() {
		return object;
	}
	
	
}
