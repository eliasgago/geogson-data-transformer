package com.eliasgago.geogson;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.matcher.LocationMerger;
import com.eliasgago.geogson.parser.GalicianMunicipalityDataParser;
import com.eliasgago.geogson.parser.GalicianMunicipalityPolygonsParser;

public class GalicianMunicipalityMergerTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianMunicipalityPolygonsParser municipalitiyParser = new GalicianMunicipalityPolygonsParser();
		Locations municipalities = municipalitiyParser.loadData();

		assertNotNull(municipalities);
		assertNotEquals(0, municipalities.size());
		
		GalicianMunicipalityDataParser postalCodeParser = new GalicianMunicipalityDataParser();
		Locations postalCodes = postalCodeParser.loadData();

		assertNotNull(postalCodes);
		assertNotEquals(0, postalCodes.size());
		
		LocationMerger matcher = new LocationMerger();
		matcher.mergeData(municipalities, postalCodes);
	}

}
