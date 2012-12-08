package org.capcaval.ermine.mvc.view.views;

import java.util.ArrayList;
import java.util.List;

public class ViewStatistics {
	public long time1;
	public long time2;
	public String name;
	public long counter;
	public ViewStatistics[] children = new ViewStatistics[0];
	public long lastFPSTime;
	public long lastFPSCounter;
	public double fps;
	
	public String toString(){
		StringBuffer stats = new StringBuffer();
		String[] strArray = this.toStringArray();
		for(String line : strArray){
			stats.append(line + "\n");
		}
		
		return stats.toString();
	}
	public String[] toStringArray(){
		List<String> list = new ArrayList<String>();
		list.add("name : " + this.name);
		list.add("\tFPS : " + this.fps);
		list.add("\tlast Render time (ms): " + ((float)(this.time2-this.time1)/1000000));
		list.add("\tcounter : " + this.counter);
		
		for(ViewStatistics child : this.children){
			String[] childStrList = child.toStringArray();
			for(String str : childStrList){
				list.add("\t"+str);
			}
		}
		
		return list.toArray(new String[0]);
	}
}
