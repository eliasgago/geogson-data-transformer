package com.eliasgago.geogson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.github.filosganga.geogson.model.FeatureCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class GalicianMunicipalityParser {

	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder()
				   .registerTypeAdapterFactory(new GeometryAdapterFactory())
				   .create();
		
		try {
			JsonReader reader = new JsonReader(new FileReader("/Users/elias/Proxectos/Test/d3/municipios_de_galicia.geojson"));

			FeatureCollection completeCollection = gson.fromJson(reader, FeatureCollection.class);
			
			for (Iterator iterator = completeCollection.features().iterator(); iterator.hasNext();) {
				Feature feature = (Feature) iterator.next();
				System.out.println(feature.properties().get("municipio"));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String json = "{\"type\":\"Point\",\"coordinates\": [23.5,20.125]}";

//		String json = gson.toJson(point);
//
//		Geometry geometry = gson.fromJson(json); // It will be an instance of Point.

	}

}
