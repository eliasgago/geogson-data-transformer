package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.parser.GalicianMunicipalityDataParser;

public class GalicianMunicipalityDataParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityDataParser parser = new GalicianMunicipalityDataParser();
		Locations places = parser.loadData();
		assertNotNull(places);
		assertNotEquals(0, places.size());
	}

}
