package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.matcher.LocationMatcher;

public class GalicianMunicipalityMatcherTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityParser municipalitiyParser = new GalicianMunicipalityParser();
		Locations municipalities = municipalitiyParser.loadData();

		assertNotNull(municipalities);
		assertNotEquals(0, municipalities.size());
		
		GalicianPostalCodeParser postalCodeParser = new GalicianPostalCodeParser();
		Locations postalCodes = postalCodeParser.loadData();

		assertNotNull(postalCodes);
		assertNotEquals(0, postalCodes.size());
		
		LocationMatcher matcher = new LocationMatcher();
		matcher.mergeData(municipalities, postalCodes);
	}

}
