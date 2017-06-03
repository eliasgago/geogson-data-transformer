package com.eliasgago.geogson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.github.filosganga.geogson.model.Coordinates;
import com.github.filosganga.geogson.model.Point;
import com.github.filosganga.geogson.model.positions.SinglePosition;
import com.opencsv.CSVReader;

public class GalicianMunicipalityCoordinatesParser {
	
	public static final String POSTAL_CODES_GALICIA_FILENAME = "features/world/spain/galicia/municipalities_coordinates.csv";

	public Locations loadData() {

		Locations placesList = new Locations();
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(POSTAL_CODES_GALICIA_FILENAME).getFile());

			CSVReader reader = new CSVReader(new FileReader(file));
            String[] line;
            while ((line = reader.readNext()) != null) {
                Location place = new Location();
				place.setName(line[0]);
				place.setPostalCode(line[2]);
				double lon = Double.valueOf(line[3]);
				double lat = Double.valueOf(line[4]);
				place.setCoordinates(new Point(new SinglePosition(Coordinates.of(lon, lat))));
				
				placesList.add(place);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return placesList;
	}

}
