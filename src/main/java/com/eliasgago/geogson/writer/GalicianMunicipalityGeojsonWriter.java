package com.eliasgago.geogson.writer;

import java.util.ArrayList;
import java.util.List;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.github.filosganga.geogson.model.FeatureCollection;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

abstract public class GalicianMunicipalityGeojsonWriter {

	public String writeData(Locations locations) {
		Gson gson = new GsonBuilder()
		   .registerTypeAdapterFactory(new GeometryAdapterFactory())
		   .create();
		
		List<Feature> features = new ArrayList<Feature>();
		for(Location location : locations){
			features.add(getFeature(location));
		}
		
		FeatureCollection featureCollection = new FeatureCollection(features);
		return gson.toJson(featureCollection).toString();
		
	}
	
	abstract protected Feature getFeature(Location location);

}
