package com.eliasgago.geogson.helper

import java.util.HashMap
import java.util.List
import java.util.Map

import com.eliasgago.geogson.domain.Location
import com.github.filosganga.geogson.model.Geometry
import com.github.filosganga.geogson.model.Point

class LocationHelper {

	public static Location getLocationMerged(Location location, List<Location> locationList) {
		for(Location locationToMerge: locationList){
			if(location.equals(locationToMerge)){
				Location mergedLocation = new Location();
				mergedLocation.setName(location.getName());
				mergedLocation.setPostalCode(getPostalCode(location, locationToMerge));
				mergedLocation.setCode(getCode(location, locationToMerge));
				mergedLocation.setData(getData(location, locationToMerge));
				mergedLocation.setCoordinates(getCoordinates(location, locationToMerge));
				mergedLocation.setArea(getArea(location, locationToMerge));
				
				return mergedLocation
			}
		}
		return location
	}
	
	private static String getPostalCode(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getPostalCode() !=  null)
			return locationOriginal.getPostalCode();
		else if(locationToMerge.getPostalCode() !=  null)
			return locationToMerge.getPostalCode();
		else return null;
	}
	
	private static String getCode(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getCode() !=  null)
			return locationOriginal.getCode();
		else if(locationToMerge.getCode() !=  null)
			return locationToMerge.getCode();
		else return null;
	}

	private static Map<String, Object> getData(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getData() ==  null)
			return locationToMerge.getData();
		else if(locationToMerge.getData() ==  null)
			return locationOriginal.getData();
		else {
			Map<String, Object> result = new HashMap<String, Object>();
			result.putAll(locationOriginal.getData());
			result.putAll(locationToMerge.getData());
			return result;
		}
	}

	private static Point getCoordinates(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getCoordinates() !=  null)
			return locationOriginal.getCoordinates();
		else if(locationToMerge.getCoordinates() !=  null)
			return locationToMerge.getCoordinates();
		else return null;
	}

	private static Geometry getArea(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getArea() !=  null)
			return locationOriginal.getArea();
		else if(locationToMerge.getArea() !=  null)
			return locationToMerge.getArea();
		else return null;
	}

}
