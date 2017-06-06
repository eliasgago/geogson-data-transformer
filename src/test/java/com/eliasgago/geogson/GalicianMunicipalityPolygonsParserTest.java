package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.matcher.LocationMerger;
import com.eliasgago.geogson.parser.GalicianMunicipalityDataParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityPointsParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityPolygonsParser;
import com.eliasgago.geogson.writer.GalicianMunicipalityGeojsonWriter;

public class GalicianMunicipalityPolygonsParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityPolygonsParser parser = new GalicianMunicipalityPolygonsParser();
		Locations places = parser.loadData();
		assertNotNull(places);
		assertNotEquals(0, places.size());
	}

}
