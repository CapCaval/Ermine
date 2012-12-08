package org.capcaval.ermine.mvc.model._impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.capcaval.ermine.mvc.model.DataTransformer;
import org.capcaval.ermine.mvc.model.Model;
import org.capcaval.ermine.mvc.model.ModelEvents;


public class ModelImpl<T> implements Model <T> {
	
	protected List<T> dataList;
	protected Map<Integer, T> dataMap;
	protected List<ModelEvents<T>> ObserverList;
	protected Map<Class<?>, DataTransformer<?,?>> transformerMap;


	public ModelImpl(){
		this.init();
	}
	
	public void init(){
		// allocate members
		this.dataMap = new HashMap<Integer, T>();
		this.dataList = new ArrayList<T>();
		this.ObserverList = new ArrayList<ModelEvents<T>>();
		this.transformerMap = new HashMap<Class<?>, DataTransformer<?,?>>();
	}
	
	public T getData(int key) {
		return this.dataMap.get(new Integer(key));
	}

	public ModelEvents<T> getModelEvents() {
		return null;
	}

	public void notifyState() {
		// retrieve the data to notify
		List<T> alldata = new ArrayList<T>(this.dataMap.values());	
		// call them with the current data
		// for each observer call them
		for( ModelEvents<T> observer :  this.ObserverList){
			observer.dataState(alldata);
		}

	}

	public void putData(int key, T obj) {
		this.dataMap.put(new Integer(key), obj);
	}

	public void setDataTransformer(Class<?> dataType,
			DataTransformer<?,?> transformer) {
		// 
		this.transformerMap.put(dataType, transformer);

	}

	public void updateData(int key, T obj) {
		// 
	}



	public void addSubscriber(ModelEvents<T> events) {
		this.ObserverList.add(events);
	}

	public void addTypedData(T obj) {
		
	}

	public List<T> getAllData() {
		return  new ArrayList<T>(this.dataMap.values());
	}

	public void addData(T obj) {
		this.addData(obj, true);
	}
	
	protected void addData(T obj, boolean performedNotification) {
		int key = this.generateKey();
		
		// add them inside the arrayList 
		this.dataList.add(obj);
		// add it inside the hashmap
		this.dataMap.put(key, obj);
		
		// perform notification if asked
		if( performedNotification == true){
			// create a list instance for notification
			List<T> list = new ArrayList<T>(1);
			list.add(obj);
			this.notifyCreated(list);
		}
	}

	public void addData(List<T> objectList) {
		for(T o : objectList){
			this.addData(o, false);
		}
		// now notify the created list
		this.notifyCreated(objectList);
	}

	protected void notifyCreated(List<T> objects){
		// for each observer call them
		for( ModelEvents<T> observer :  this.ObserverList){
			observer.dataCreated(objects);
		}
	}
	
	public <I> void addDataTransformer(DataTransformer<I, T> dt) {
		
	}

	public void removeData(int key) {
		// first retrieve the deleted data
		T obj = this.getData(key);
		List<T> list = new ArrayList<T>();
		list.add(obj);
		// notify the subscriber to the deletion
		this.notifyDeleted(list);
		
	}

	public void addData(int key, T obj) {
		
	}

	protected int generateKey(){
		Random rnd = new Random();
		
		int key;
		// get a key until it is not already used inside the model
		do{
			key = rnd.nextInt();
		}while( this.dataMap.containsKey(key)==true );
		
		return key;
	}

	public void removeAllData() {
		// get all the data
		List<T> allData = this.getAllData();
		// clean the map
		this.dataMap.clear();
		
		// notify the subscribers
		this.notifyDeleted(allData);
	}
	
	protected void notifyDeleted(List<T> objects){
		// for each observer call them
		for( ModelEvents<T> observer :  this.ObserverList){
			observer.dataDeleted(objects);
		}
	}

	public void removeSubscriber(ModelEvents<T> events) {
		this.ObserverList.remove(events);
	}

	
}
