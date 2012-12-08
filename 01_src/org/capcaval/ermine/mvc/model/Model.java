package org.capcaval.ermine.mvc.model;


import java.util.List;

/**
 * @author Mik
 *
 */
 
public interface Model <T>{
	
	/**
	 * @param key
	 * @return
	 */
	public T getData(int key);
	/**
	 * @param key
	 * @param obj
	 * @return
	 */
	
	
	public List<T> getAllData();
	public void addData(T obj);
	public void addData(List<T> obj);
	public void addData( int key, T obj);

	public void updateData(int key, T obj);
	public void removeData(int key);
	public void removeAllData();
	
	public void addSubscriber(ModelEvents<T> events);
	public void removeSubscriber(ModelEvents<T> events);
	
	public ModelEvents<T> getModelEvents();

	public void notifyState();
	

	public <I> void addDataTransformer(DataTransformer<I, T> dt);
	
}
