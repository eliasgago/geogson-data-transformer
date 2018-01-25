package com.eliasgago.geogson.batch.base;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Autowired

import com.eliasgago.geogson.domain.Location
import com.eliasgago.geogson.domain.Locations
import com.github.filosganga.geogson.gson.GeometryAdapterFactory
import com.github.filosganga.geogson.model.Feature
import com.github.filosganga.geogson.model.FeatureCollection
import com.google.common.collect.ImmutableMap
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

abstract class LocationWriter<T> implements ItemWriter<Location> {
	
	private static final Logger log = LoggerFactory.getLogger(LocationWriter.class);

	private StepExecution stepExecution;
	
	@Autowired
	Locations locations

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
	
	@Override
	public void write(List<? extends Location> items) throws Exception {
		
		locations.locations = items
		
		Gson gson = new GsonBuilder()
		.registerTypeAdapterFactory(new GeometryAdapterFactory())
		.create();
	 
		 List<Feature> features = new ArrayList<Feature>();
		 for(Location location : locations){
			 features.add(getFeature(location));
		 }
		 
		 FeatureCollection featureCollection = new FeatureCollection(features);

		 try {
			 FileWriter writer = new FileWriter(new File("result.json"));
			 writer.append(gson.toJson(featureCollection).toString());
			 writer.close();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 
	}
	
	private Feature getFeature(Location location) {
		Gson gson = new GsonBuilder()
			.registerTypeAdapterFactory(new GeometryAdapterFactory())
			.create();
		
		HashMap<String, JsonElement> data = new HashMap<String, JsonElement>();
		if(location.getData() != null) {
			location.getData().each{ key, value -> 
				data.put(key, new JsonPrimitive(value.toString()));
			};
		}

		 ImmutableMap<String, JsonElement> properties = ImmutableMap.<String, JsonElement>builder()
					.put("name", location.getName() != null ? new JsonPrimitive(location.getName()) : new JsonPrimitive(""))
					.put("code", location.getCode() != null ? new JsonPrimitive(location.getCode()) : new JsonPrimitive(""))
					.put("postal_code", location.getPostalCode() != null ? new JsonPrimitive(location.getPostalCode()) : new JsonPrimitive(""))
					.putAll(data)
					.build();
		 
		 String json = "{\"type\": \"Feature\"," +
				 "\"properties\":" + gson.toJson(properties) + 
	 			"," +
				 "\"geometry\":" + gson.toJson(location.getArea()) +
				 "}";
		 log.info(json);
		 return gson.fromJson(json, Feature.class);
	}
	
}