package com.eliasgago.geogson.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasgago.geogson.domain.Locations;
import com.eliasgago.geogson.parser.GalicianMunicipalityPointsParser;

@Service
public class GetGalicianMunicipalityPointsService {

	@Autowired
	GalicianMunicipalityPointsParser galicianMunicipalityPointsParser;
	
    public void execute() {
    	Locations locations = galicianMunicipalityPointsParser.loadData();
    	System.out.println(locations);
    }

}