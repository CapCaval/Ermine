package org.capcaval.ermine.mvc.model;

import java.util.List;

public interface ModelEvents <T>{
	/**
	 * @param inInstance
	 * @return
	 */
	public void dataCreated(List<T> instanceList);
	/**
	 * @param inInstance
	 * @param outInstance
	 * @return
	 */
	public void dataUpdated(List<T> instanceList);
	/**
	 * @param inInstance
	 * @param outInstance
	 * @return
	 */
	public void dataDeleted(List<T> instanceList);

	public void dataState(List<T> instanceList);
	
}
