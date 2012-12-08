package org.capcaval.ermine.mvc.view.layers._impl.j2d._test;

import org.capcaval.ermine.mvc.view.layers._impl.j2d.GridLayer2DImpl;
import org.junit.Assert;


public class GridTest {
	@org.junit.Test
	public void rangeValuesTest(){
		Double[] values = GridLayer2DImpl.computeRangeValues(
				90, 
				300,
				50);
		
		Assert.assertArrayEquals(
				values, 
				new Double[]{100d, 150d, 200d, 250d, 300d});
	}

}
