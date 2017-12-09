package com.eliasgago.geogson.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.parser.GalicianPoblacionDataParser;

@Service
public class GetGalicianPopulationService {

	@Autowired
	GalicianPoblacionDataParser galicianPoblacionDataParser;
	
    public void execute() {
    	Locations locations = galicianPoblacionDataParser.loadData();
    	System.out.println(locations);
    }

}