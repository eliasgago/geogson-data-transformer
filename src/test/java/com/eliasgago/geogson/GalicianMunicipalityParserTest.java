package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.matcher.LocationMatcher;

public class GalicianMunicipalityParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityParser parser = new GalicianMunicipalityParser();
		Locations places = parser.loadData();
		assertNotNull(places);
		assertNotEquals(0, places.size());
	}


	@Test
	public void load_data_and_merge_and_write() {
		GalicianMunicipalityParser municipalitiyParser = new GalicianMunicipalityParser();
		Locations municipalities = municipalitiyParser.loadData();

		assertNotNull(municipalities);
		assertNotEquals(0, municipalities.size());
		
		GalicianPostalCodeParser postalCodeParser = new GalicianPostalCodeParser();
		Locations postalCodes = postalCodeParser.loadData();

		assertNotNull(postalCodes);
		assertNotEquals(0, postalCodes.size());
		
		LocationMatcher matcher = new LocationMatcher();
		Locations mergeMunicipalitiesAndCodes = matcher.mergeData(municipalities, postalCodes);

		GalicianMunicipalityCoordinatesParser municipalitiyCoordinatesParser = new GalicianMunicipalityCoordinatesParser();
		Locations municipalitiesCoordinates = municipalitiyCoordinatesParser.loadData();

		assertNotNull(municipalitiesCoordinates);
		assertNotEquals(0, municipalitiesCoordinates.size());

		Locations mergeResult = matcher.mergeData(municipalitiesCoordinates, mergeMunicipalitiesAndCodes);
		System.out.println(mergeResult);
		
		String result = municipalitiyParser.writeData(mergeResult);
		try {
			FileWriter writer = new FileWriter(new File("test.json"));
			writer.append(result);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
