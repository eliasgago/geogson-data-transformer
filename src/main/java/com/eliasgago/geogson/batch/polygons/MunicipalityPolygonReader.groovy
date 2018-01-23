package com.eliasgago.geogson.batch.polygons

import javax.annotation.PostConstruct

import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.NonTransientResourceException
import org.springframework.batch.item.ParseException
import org.springframework.batch.item.UnexpectedInputException
import org.springframework.stereotype.Component

import com.github.filosganga.geogson.gson.GeometryAdapterFactory
import com.github.filosganga.geogson.model.Feature
import com.github.filosganga.geogson.model.FeatureCollection
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader

@Component
class MunicipalityPolygonReader implements ItemReader<Feature> {

	public static String MUNICIPALITIES_GALICIA_POLYGONS_FILENAME = "features/world/spain/galicia/municipality_polygons.geojson";

	List<Feature> featuresList;
	private int nextFeature;

	@PostConstruct
	final void setConfiguration() {

		Gson gson = new GsonBuilder()
				.registerTypeAdapterFactory(new GeometryAdapterFactory())
				.create();
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(MUNICIPALITIES_GALICIA_POLYGONS_FILENAME).getFile());
		
		JsonReader reader = new JsonReader(new FileReader(file));

		featuresList = ((FeatureCollection) gson.fromJson(reader, FeatureCollection.class)).features();
	}

	@Override
	public Feature read()
	throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Feature nextMunicipalityPolygon = null;

		if (nextFeature < featuresList.size()) {
			nextMunicipalityPolygon = featuresList.get(nextFeature);
			nextFeature++;
		}

		return nextMunicipalityPolygon;
	}
}
