package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.eliasgago.geogson.domain.Locations;

public class GalicianPostalCodeParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianPostalCodeParser parser = new GalicianPostalCodeParser();
		Locations places = parser.loadData();
		assertNotNull(places);
		assertNotEquals(0, places.size());
	}

}
