package org.capcaval.ermine.mvc.view.layers._impl.j2d.aspects;

import java.awt.image.BufferedImage;

public interface CacheSwapAccessor {
	
	public BufferedImage getWorkCache();
	public void setWorkCache(BufferedImage workCache);
	
	public BufferedImage getAvailableCache();
	public  void setAvailableCache(BufferedImage availableCache);
	

}
