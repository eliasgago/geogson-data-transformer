package com.eliasgago.geogson.parser;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.github.filosganga.geogson.model.Coordinates;
import com.github.filosganga.geogson.model.Point;
import com.github.filosganga.geogson.model.positions.SinglePosition;
import com.opencsv.CSVReader;

@Service
public class GalicianMunicipalityPointsParser {
	
	@Value(value = "classpath:features/world/spain/galicia/municipality_points.csv")
	private Resource municipalityPoints;

	public Locations loadData() {

		Locations placesList = new Locations();
		
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(municipalityPoints.getInputStream()));
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
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return placesList;
	}

}
