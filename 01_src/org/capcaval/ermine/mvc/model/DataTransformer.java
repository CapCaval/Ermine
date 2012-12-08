package org.capcaval.ermine.mvc.model;


/**
 * @author Mik
 *
 */
public interface DataTransformer<I,O> {
	
	/**
	 * @param inInstance
	 * @return
	 */
	public O dataCreated(I inInstance);
	/**
	 * @param inInstance
	 * @param outInstance
	 * @return
	 */
	public O dataUpdated(I inInstance, O outInstance);
	/**
	 * @param inInstance
	 * @param outInstance
	 * @return
	 */
	public O dataDeleted(I inInstance, O outInstance);

}
