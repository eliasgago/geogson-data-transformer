package com.eliasgago.geogson.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.github.filosganga.geogson.model.FeatureCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class GalicianMunicipalityPolygonsParser {
	
	public static final String MUNICIPALITIES_GALICIA_FILENAME = "features/world/spain/galicia/municipality_polygons.geojson";

	public Locations loadData() {
		
		Locations placesList = new Locations();
		
		Gson gson = new GsonBuilder()
				   .registerTypeAdapterFactory(new GeometryAdapterFactory())
				   .create();
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(MUNICIPALITIES_GALICIA_FILENAME).getFile());
			JsonReader reader = new JsonReader(new FileReader(file));

			FeatureCollection completeCollection = gson.fromJson(reader, FeatureCollection.class);
			
			for (Iterator<Feature> iterator = completeCollection.features().iterator(); iterator.hasNext();) {
				Feature feature = iterator.next();
				
				Location place = new Location();
				String name = feature.properties().get("municipio").toString();
				place.setName(name.substring(1, name.length() - 1));
				place.setArea(feature.geometry());
				
				placesList.add(place);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return placesList;
		
	}
	
}
