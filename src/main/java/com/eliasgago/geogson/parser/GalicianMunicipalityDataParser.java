package com.eliasgago.geogson.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.opencsv.CSVReader;

public class GalicianMunicipalityDataParser {
	
	public static final String POSTAL_CODES_GALICIA_FILENAME = "features/world/spain/galicia/municipality_data.csv";

	public Locations loadData() {

		Locations placesList = new Locations();
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(POSTAL_CODES_GALICIA_FILENAME).getFile());

			CSVReader reader = new CSVReader(new FileReader(file));
            String[] line;
            while ((line = reader.readNext()) != null) {
                Location place = new Location();
				place.setName(line[2]);
				place.setCode(line[1]);
				
				placesList.add(place);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return placesList;
	}

}
