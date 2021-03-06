package com.eliasgago.geogson.writer;

import java.util.HashMap;

import com.eliasgago.geogson.domain.Location;
import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class GalicianMunicipalityCoordinatesGeojsonWriter extends
		GalicianMunicipalityGeojsonWriter {

	@Override
	protected Feature getFeature(Location location) {
		JsonArray jsonCoordinates = new JsonArray();
	    if(location.getCoordinates() != null){
	    	jsonCoordinates.add(location.getCoordinates().coordinates().getLat());
	    	jsonCoordinates.add(location.getCoordinates().coordinates().getLon());
	    	
	    	Gson gson = new GsonBuilder()
	    	   .registerTypeAdapterFactory(new GeometryAdapterFactory())
	    	   .create();

			HashMap<String, JsonElement> data = new HashMap<String, JsonElement>();
			if(location.getData() != null) {
				location.getData().forEach((key, value) -> {
					data.put(key, new JsonPrimitive(value.toString())); 
				});
			}

	    	ImmutableMap<String, JsonElement> properties = ImmutableMap.<String, JsonElement>builder()
				    .put("name", location.getName() != null ? new JsonPrimitive(location.getName()) : new JsonPrimitive("")) 
				    .put("code", location.getCode() != null ? new JsonPrimitive(location.getCode()) : new JsonPrimitive("")) 
				    .put("postal_code", location.getPostalCode() != null ? new JsonPrimitive(location.getPostalCode()) : new JsonPrimitive(""))
				    .putAll(data)
				    .build();
	    	
		 	String json = "{\"type\": \"Feature\","
		 			+ "\"properties\":" + gson.toJson(properties) + ","
	    			+ "\"geometry\":{\"type\":\"Point\",\"coordinates\": [" + location.getCoordinates().coordinates().getLat() + "," + location.getCoordinates().coordinates().getLon() + "]}}";
		 	
	    	return gson.fromJson(json, Feature.class); 
	    }
	    return null;
	}
	
}
