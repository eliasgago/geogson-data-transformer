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
import com.eliasgago.geogson.matcher.LocationMerger;
import com.eliasgago.geogson.parser.GalicianMunicipalityDataParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityPointsParser;
import com.eliasgago.geogson.writer.GalicianMunicipalityGeojsonWriter;

public class GalicianMunicipalityPointsParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityPointsParser parser = new GalicianMunicipalityPointsParser();
		Locations locations = parser.loadData();
		System.out.println(locations);
		assertNotNull(locations);
		assertNotEquals(0, locations.size());
	}
	
}
