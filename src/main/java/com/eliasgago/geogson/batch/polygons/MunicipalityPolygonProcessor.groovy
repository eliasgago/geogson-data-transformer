package com.eliasgago.geogson.batch.polygons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component

import com.eliasgago.geogson.batch.base.LocationProcessor
import com.eliasgago.geogson.domain.Location
import com.eliasgago.geogson.helper.LocationHelper
import com.github.filosganga.geogson.model.Feature

@Component
class MunicipalityPolygonProcessor extends LocationProcessor<Feature> {

    private static final Logger log = LoggerFactory.getLogger(MunicipalityPolygonProcessor.class);

    @Override
    public Location process(final Feature municipalityPolygon) throws Exception {
		
		final String name = municipalityPolygon.properties().get("municipio").toString();
		
		Location location = new Location(
			name: name.substring(1, name.length() - 1),
			area: municipalityPolygon.geometry()
		);
		
		return LocationHelper.getLocationMerged(location, finalListLocations)
    }

}