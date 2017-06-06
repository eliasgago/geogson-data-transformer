package com.eliasgago.geogson.matcher;

import java.util.Map;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.github.filosganga.geogson.model.Geometry;
import com.github.filosganga.geogson.model.Point;

public class LocationMerger {

	public Locations mergeData(Locations listOriginal, Locations listToMerge) {
		
		Locations result = new Locations();
		
		for(Location locationOriginal: listOriginal){
			boolean found = false;
			for(Location locationToMerge: listToMerge){
				if(locationOriginal.equals(locationToMerge)){
					Location mergedLocation = new Location();
					mergedLocation.setName(locationOriginal.getName());
					mergedLocation.setPostalCode(getPostalCode(locationOriginal, locationToMerge));
					mergedLocation.setCode(getCode(locationOriginal, locationToMerge));
					mergedLocation.setData(getData(locationOriginal, locationToMerge));
					mergedLocation.setCoordinates(getCoordinates(locationOriginal, locationToMerge));
					mergedLocation.setArea(getArea(locationOriginal, locationToMerge));
				    
					result.add(mergedLocation);
					found = true;
					break;
				}
			}
			if(!found){
				System.out.println("NOT FOUND: " + locationOriginal);
			}
		}
		
		return result;
	}

	private String getPostalCode(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getPostalCode() !=  null)
			return locationOriginal.getPostalCode();
		else if(locationToMerge.getPostalCode() !=  null)
			return locationToMerge.getPostalCode();
		else return null;
	}
	
	private String getCode(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getCode() !=  null)
			return locationOriginal.getCode();
		else if(locationToMerge.getCode() !=  null)
			return locationToMerge.getCode();
		else return null;
	}

	private Map<String, Object> getData(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getData() !=  null)
			return locationOriginal.getData();
		else if(locationToMerge.getData() !=  null)
			return locationToMerge.getData();
		else return null;
	}

	private Point getCoordinates(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getCoordinates() !=  null)
			return locationOriginal.getCoordinates();
		else if(locationToMerge.getCoordinates() !=  null)
			return locationToMerge.getCoordinates();
		else return null;
	}

	private Geometry getArea(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getArea() !=  null)
			return locationOriginal.getArea();
		else if(locationToMerge.getArea() !=  null)
			return locationToMerge.getArea();
		else return null;
	}

}
