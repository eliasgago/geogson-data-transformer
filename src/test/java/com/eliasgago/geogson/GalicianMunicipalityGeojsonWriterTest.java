package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.matcher.LocationMerger;
import com.eliasgago.geogson.parser.GalicianDataParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityDataParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityPointsParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityPolygonsParser;
import com.eliasgago.geogson.writer.GalicianMunicipalityAreaGeojsonWriter;
import com.eliasgago.geogson.writer.GalicianMunicipalityCoordinatesGeojsonWriter;
import com.eliasgago.geogson.writer.GalicianMunicipalityGeojsonWriter;

public class GalicianMunicipalityGeojsonWriterTest {
	
	private Locations mergeResult;

	@Before
	public void setUp(){
		GalicianDataParser dataParser = new GalicianDataParser();
		Locations data = dataParser.loadData();

		assertNotNull(data);
		assertNotEquals(0, data.size());

		GalicianMunicipalityDataParser municipalityDataParser = new GalicianMunicipalityDataParser();
		Locations municipalityData = municipalityDataParser.loadData();

		assertNotNull(municipalityData);
		assertNotEquals(0, municipalityData.size());

		LocationMerger matcher = new LocationMerger();
		Locations dataLocations = matcher.mergeData(municipalityData, data);

		GalicianMunicipalityPointsParser municipalitiyPointsParser = new GalicianMunicipalityPointsParser();
		Locations municipalitiesPointsLocations = municipalitiyPointsParser.loadData();

		assertNotNull(municipalitiesPointsLocations);
		assertNotEquals(0, municipalitiesPointsLocations.size());
		
		Locations pointsAndDataLocations = matcher.mergeData(dataLocations, municipalitiesPointsLocations);

		GalicianMunicipalityPolygonsParser municipalityPolygonsParser = new GalicianMunicipalityPolygonsParser();
		Locations municipalityPolygonsLocations = municipalityPolygonsParser.loadData();

		assertNotNull(municipalityPolygonsLocations);
		assertNotEquals(0, municipalityPolygonsLocations.size());
		
		mergeResult = matcher.mergeData(pointsAndDataLocations, municipalityPolygonsLocations);

		System.out.println(mergeResult);
		assertNotNull(mergeResult);
		assertNotEquals(0, mergeResult.size());
		
	}

	@Test
	public void load_data_and_merge_and_write_with_coordinates() {
		GalicianMunicipalityGeojsonWriter galicianMunicipalityGeojsonWriter = new GalicianMunicipalityCoordinatesGeojsonWriter();
		String result = galicianMunicipalityGeojsonWriter.writeData(mergeResult);
		try {
			FileWriter writer = new FileWriter(new File("test_coordinates.json"));
			writer.append(result);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void load_data_and_merge_and_write_with_areas() {
		GalicianMunicipalityGeojsonWriter galicianMunicipalityGeojsonWriter = new GalicianMunicipalityAreaGeojsonWriter();
		String result = galicianMunicipalityGeojsonWriter.writeData(mergeResult);
		try {
			FileWriter writer = new FileWriter(new File("test_areas.json"));
			writer.append(result);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
