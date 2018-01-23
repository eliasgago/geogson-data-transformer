package com.eliasgago.geogson.batch.population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component

import com.eliasgago.geogson.batch.base.LocationProcessor
import com.eliasgago.geogson.domain.Location
import com.eliasgago.geogson.helper.LocationHelper

@Component
class MunicipalityPopulationProcessor  extends LocationProcessor<MunicipalityPopulation> {

    private static final Logger log = LoggerFactory.getLogger(MunicipalityPopulationProcessor.class);
	
    @Override
    public Location process(final MunicipalityPopulation municipalityPopulation) throws Exception {
		
		final String code = municipalityPopulation.code
        final String name = municipalityPopulation.name.toLowerCase()
		final String population = municipalityPopulation.population

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("population", population);
		
		final Location location = new Location(
			name: name,
			code: code,
			data: data
		);
		
        log.info("Converting (" + municipalityPopulation + ") into (" + location + ")");
		
		return LocationHelper.getLocationMerged(location, finalListLocations)
    }

}