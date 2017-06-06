package com.eliasgago.geogson.writer;

import com.eliasgago.geogson.domain.Location;
import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class GalicianMunicipalityAreaGeojsonWriter extends
		GalicianMunicipalityGeojsonWriter {

	@Override
	protected Feature getFeature(Location location) {
		Gson gson = new GsonBuilder()
	 	   .registerTypeAdapterFactory(new GeometryAdapterFactory())
	 	   .create();

	 	ImmutableMap<String, JsonElement> properties = ImmutableMap.<String, JsonElement>builder()
				    .put("name", location.getName() != null ? new JsonPrimitive(location.getName()) : new JsonPrimitive("")) 
				    .put("code", location.getCode() != null ? new JsonPrimitive(location.getCode()) : new JsonPrimitive("")) 
				    .put("postal_code", location.getPostalCode() != null ? new JsonPrimitive(location.getPostalCode()) : new JsonPrimitive(""))
				    .build();
	 	
	 	String json = "{\"type\": \"Feature\","
	 			+ "\"properties\":" + gson.toJson(properties) + ","
	 			+ "\"geometry\":" + gson.toJson(location.getArea())
	 			+ "}";
	 	
	 	return gson.fromJson(json, Feature.class); 
	}

	
}
