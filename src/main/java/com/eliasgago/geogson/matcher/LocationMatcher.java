package com.eliasgago.geogson.matcher;

import java.util.Optional;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.github.filosganga.geogson.model.Geometry;
import com.github.filosganga.geogson.model.Point;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class LocationMatcher {

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
					mergedLocation.setCoordinates(getCoordinates(locationOriginal, locationToMerge));
					mergedLocation.setFeature(createFeature(mergedLocation, locationOriginal, locationToMerge));
				    
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

	private Point getCoordinates(Location locationOriginal,
			Location locationToMerge) {
		if(locationOriginal.getCoordinates() !=  null)
			return locationOriginal.getCoordinates();
		else if(locationToMerge.getCoordinates() !=  null)
			return locationToMerge.getCoordinates();
		else return null;
	}

	private Feature createFeature(Location finalLocation, Location locationOriginal,
			Location locationToMerge) {
		JsonArray jsonCoordinates = new JsonArray();
	    if(finalLocation.getCoordinates() != null){
	    	jsonCoordinates.add(finalLocation.getCoordinates().coordinates().getLat());
	    	jsonCoordinates.add(finalLocation.getCoordinates().coordinates().getLon());
	    	
	    	Gson gson = new GsonBuilder()
	    	   .registerTypeAdapterFactory(new GeometryAdapterFactory())
	    	   .create();
	    	
	    	String json = "{\"type\": \"Feature\","
	    			+ "\"properties\":{},"
	    			+ "\"geometry\":{\"type\":\"Point\",\"coordinates\": [" + finalLocation.getCoordinates().coordinates().getLat() + "," + finalLocation.getCoordinates().coordinates().getLon() + "]}}";
	    	ImmutableMap<String, JsonElement> properties = ImmutableMap.<String, JsonElement>builder()
				    .put("name", finalLocation.getName() != null ? new JsonPrimitive(finalLocation.getName()) : new JsonPrimitive("")) 
				    .put("code", finalLocation.getCode() != null ? new JsonPrimitive(finalLocation.getCode()) : new JsonPrimitive("")) 
				    .put("postal_code", finalLocation.getPostalCode() != null ? new JsonPrimitive(finalLocation.getPostalCode()) : new JsonPrimitive(""))
				    .build();
	    	return gson.fromJson(json, Feature.class); 
	    }
	    return null;
	    /*Feature feature = null;
	    if(locationOriginal.getFeature() !=  null)
	    	feature = locationOriginal.getFeature();
		else if(locationToMerge.getFeature() !=  null)
			feature = locationToMerge.getFeature();
		else 
			return null;
		ImmutableMap<String, JsonElement> properties = ImmutableMap.<String, JsonElement>builder()
			    .put("name", finalLocation.getName() != null ? new JsonPrimitive(finalLocation.getName()) : new JsonPrimitive("")) 
			    .put("code", finalLocation.getCode() != null ? new JsonPrimitive(finalLocation.getCode()) : new JsonPrimitive("")) 
			    .put("postal_code", finalLocation.getPostalCode() != null ? new JsonPrimitive(finalLocation.getPostalCode()) : new JsonPrimitive(""))
			    .put("coordinates", jsonCoordinates)
			    .build();
		return new Feature(feature.geometry(), properties, feature.id());*/
	}

}
