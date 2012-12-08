package org.capcaval.ermine.mvc.model._test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.capcaval.ermine.mvc.model.Model;
import org.capcaval.ermine.mvc.model.ModelEvents;
import org.capcaval.ermine.mvc.model.ModelEventsAdapter;
import org.capcaval.ermine.mvc.model.ModelFactory;

public class ModelTest extends TestCase {
	
	
	public void testGetAllData(){
		ModelFactory f = ModelFactory.factory;
		Model<String> strModel = f.newModel(String.class);
		
		String[] inputAllData = { "Toto","Titi","Tutu"};
		
		for(int i =0; i< inputAllData.length; i++){
			strModel.addData( i , inputAllData[i]);
			}
	
		List<String> allData = strModel.getAllData();
		
		int i =0;
		for( String str : allData){
			Assert.assertEquals(str, inputAllData[i++]);
		}
		
	}

	
	public void testNotificationCreate(){
		ModelFactory f = ModelFactory.factory;
		
		// values to chezck that notification has been performed
		final AtomicBoolean creationNotified = new AtomicBoolean(false);
		final AtomicBoolean deleteNotified = new AtomicBoolean(false);		
//		final AtomicBoolean updateNotified = new AtomicBoolean(false);		
//		final AtomicBoolean stateNotified = new AtomicBoolean(false);		
		
		// allocate the model
		Model<String> strModel = f.newModel(String.class);
		
		// subscribe to events
		strModel.addSubscriber(new ModelEventsAdapter<String>(){
			@Override
			public void dataCreated(List<String> instanceList) {
				if(		(instanceList.get(0) == "One")&&
						(instanceList.get(1) == "Two")&&
						(instanceList.get(2) == "Three")&&
						(instanceList.get(3) == "Four")){
					creationNotified.set(true);					
				}
			}
			public void dataDeleted(List<String> instanceList) {
//				if(( instanceList.get(0) == "Two" )&&( instanceList.size() == 1))
				if(instanceList.size() == 4)
					deleteNotified.set(true);
			}

			public void dataState(List<String> instanceList) {
			}

			public void dataUpdated(List<String> instanceList) {
			}});
		
		
		List<String> inputAllData = new ArrayList<String>();

		inputAllData.add("One");
		inputAllData.add("Two");
		inputAllData.add("Three");
		inputAllData.add("Four");

		// create some data
		strModel.addData( inputAllData);

		// check out creation
		Assert.assertEquals(creationNotified.get(), true);
		
		strModel.removeAllData();

		// check out notification creation
		Assert.assertEquals(deleteNotified.get(), true);
		// check out that the object is not there any more
		Assert.assertEquals(strModel.getData(2),  null);
	}


	public void testNotificationDelete(){
			ModelFactory f = ModelFactory.factory;
			
			// values to chezck that notification has been performed
			final AtomicBoolean creationNotified = new AtomicBoolean(false);
			final AtomicBoolean deleteNotified = new AtomicBoolean(false);		
//			final AtomicBoolean updateNotified = new AtomicBoolean(false);		
//			final AtomicBoolean stateNotified = new AtomicBoolean(false);		
			
			// allocate the model
			Model<String> strModel = f.newModel(String.class);
			
			// subscribe to events
			strModel.addSubscriber(new ModelEvents<String>(){
	
				public void dataCreated(List<String> instanceList) {
					if(		(instanceList.get(0) == "One")&&
							(instanceList.get(1) == "Two")&&
							(instanceList.get(2) == "Three")&&
							(instanceList.get(3) == "Four")){
						creationNotified.set(true);					
					}
				}
				public void dataDeleted(List<String> instanceList) {
	//				if(( instanceList.get(0) == "Two" )&&( instanceList.size() == 1))
					if(instanceList.size() == 4)
						deleteNotified.set(true);
				}
	
				public void dataState(List<String> instanceList) {
				}
	
				public void dataUpdated(List<String> instanceList) {
				}});
			
			
			List<String> inputAllData = new ArrayList<String>();
	
			inputAllData.add("One");
			inputAllData.add("Two");
			inputAllData.add("Three");
			inputAllData.add("Four");
	
			// create some data
			strModel.addData( inputAllData);
	
			// check out creation
			Assert.assertEquals(creationNotified.get(), true);
			
			strModel.removeAllData();
	
			// check out notification creation
			Assert.assertEquals(deleteNotified.get(), true);
			// check out that the object is not there any more
			Assert.assertEquals(strModel.getData(2),  null);
		}

}
