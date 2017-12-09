package com.eliasgago.geogson.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.eliasgago.geogson.domain.Location;
import com.eliasgago.geogson.domain.Locations;
import com.opencsv.CSVReader;

public class GalicianPopulationEvolutionDataParser {
	
	public static final String GALICIA_DATA_FILENAME = "data/world/spain/galicia/evolucion_poblacion.csv";

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
				try{
					Integer population2015 = Integer.valueOf(line[2]);
					Integer population2016 = Integer.valueOf(line[3]);
					data.put("2015", population2015);
					data.put("2016", population2016);
					data.put("2016_evolution", population2016 - population2015);
					place.setData(data);
				}catch(NumberFormatException e){
					System.out.println("No registrado: " + place.getName());
				}
				
				placesList.add(place);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return placesList;
	}

}
