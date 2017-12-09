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
import com.eliasgago.geogson.parser.GalicianPoblacionDataParser;
import com.eliasgago.geogson.parser.GalicianVacasDataParser;
import com.eliasgago.geogson.writer.GalicianMunicipalityAreaGeojsonWriter;
import com.eliasgago.geogson.writer.GalicianMunicipalityGeojsonWriter;

public class GalicianVacasDataParserTest {

	@Test
	public void load_data_and_list_is_not_empty() {
		GalicianVacasDataParser parser = new GalicianVacasDataParser();
		Locations dataVacas = parser.loadData();
		assertNotNull(dataVacas);
		assertNotEquals(0, dataVacas.size());

		GalicianPoblacionDataParser dataParser = new GalicianPoblacionDataParser();
		Locations dataPoblacion = dataParser.loadData();

		LocationMerger matcher = new LocationMerger();
		Locations dataLocations = matcher.mergeData(dataPoblacion, dataVacas);

		GalicianMunicipalityDataParser municipalityDataParser = new GalicianMunicipalityDataParser();
		Locations municipalityData = municipalityDataParser.loadData();

		assertNotNull(municipalityData);
		assertNotEquals(0, municipalityData.size());

		Locations dataMunicipality = matcher.mergeData(municipalityData, dataLocations);

		GalicianMunicipalityPointsParser municipalitiyPointsParser = new GalicianMunicipalityPointsParser();
		Locations municipalitiesPointsLocations = municipalitiyPointsParser.loadData();

		assertNotNull(municipalitiesPointsLocations);
		assertNotEquals(0, municipalitiesPointsLocations.size());
		
		Locations pointsAndDataLocations = matcher.mergeData(dataMunicipality, municipalitiesPointsLocations);

		GalicianMunicipalityPolygonsParser municipalityPolygonsParser = new GalicianMunicipalityPolygonsParser();
		Locations municipalityPolygonsLocations = municipalityPolygonsParser.loadData();

		assertNotNull(municipalityPolygonsLocations);
		assertNotEquals(0, municipalityPolygonsLocations.size());
		
		Locations data = matcher.mergeData(pointsAndDataLocations, municipalityPolygonsLocations);

		
		GalicianMunicipalityGeojsonWriter galicianMunicipalityGeojsonWriter = new GalicianMunicipalityAreaGeojsonWriter();
		String result = galicianMunicipalityGeojsonWriter.writeData(data);
		try {
			FileWriter writer = new FileWriter(new File("test_vacas.json"));
			writer.append(result);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
