package com.eliasgago.geogson.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.opencsv.CSVReader;

public class GalicianVacasDataParser {
	
	public static final String GALICIA_DATA_FILENAME = "data/world/spain/galicia/galicia_numero_vacas.csv";

	public Locations loadData() {

		Locations placesList = new Locations();
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(GALICIA_DATA_FILENAME).getFile());

			CSVReader reader = new CSVReader(new FileReader(file), ';');
            String[] line;
            while ((line = reader.readNext()) != null) {
                Location place = new Location();
				place.setName(line[1]);
				place.setCode(line[0]);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("num_vacas", line[2]);
				place.setData(data);
				
				placesList.add(place);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return placesList;
	}

}
