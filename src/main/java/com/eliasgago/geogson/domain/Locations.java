package com.eliasgago.geogson.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Locations implements Iterable<Location> {

	private List<Location> locations;
	
	public Locations(){
		locations = new ArrayList<Location>();
	}
	
	public void add(Location location) {
		locations.add(location);
	}
	
	public int size() {
		return locations.size();
	}

	public Iterator<Location> iterator() {
		return locations.iterator();
	}
	
	@Override
	public String toString() {
		String result = "";
		for(Location location : locations){
			result += location + "\n";
		}
		return result;
	}

	
}
