package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.matcher.LocationMatcher;

public class GalicianPostalMunicipalityCoordinatesParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityCoordinatesParser parser = new GalicianMunicipalityCoordinatesParser();
		Locations locations = parser.loadData();
		System.out.println(locations);
		assertNotNull(locations);
		assertNotEquals(0, locations.size());
	}
	
	@Test
	public void load_data_and_merge_and_write() {
		GalicianMunicipalityCoordinatesParser municipalitiyCoordinatesParser = new GalicianMunicipalityCoordinatesParser();
		Locations municipalitiesCoordinates = municipalitiyCoordinatesParser.loadData();

		assertNotNull(municipalitiesCoordinates);
		assertNotEquals(0, municipalitiesCoordinates.size());
		
		GalicianPostalCodeParser postalCodeParser = new GalicianPostalCodeParser();
		Locations postalCodes = postalCodeParser.loadData();

		assertNotNull(postalCodes);
		assertNotEquals(0, postalCodes.size());
		
		LocationMatcher matcher = new LocationMatcher();
		Locations mergeResult = matcher.mergeData(municipalitiesCoordinates, postalCodes);

		System.out.println(mergeResult);
		assertNotNull(mergeResult);
		assertNotEquals(0, mergeResult.size());
		
	}
}
