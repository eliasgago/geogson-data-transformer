package com.eliasgago.geogson.batch.points;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component

import com.eliasgago.geogson.domain.Location
import com.github.filosganga.geogson.model.Coordinates
import com.github.filosganga.geogson.model.Point
import com.github.filosganga.geogson.model.positions.SinglePosition

@Component
class MunicipalityPointProcessor implements ItemProcessor<MunicipalityPoint, Location> {

    private static final Logger log = LoggerFactory.getLogger(MunicipalityPointProcessor.class);

    @Override
    public Location process(final MunicipalityPoint municipalityPoint) throws Exception {
		
        final String name = municipalityPoint.name.toLowerCase()
        final String postalCode = municipalityPoint.postalCode
		final Point point = new Point(new SinglePosition(Coordinates.of(municipalityPoint.longitude, municipalityPoint.latitude)))

        final Location location = new Location(
			name: name,
			postalCode: postalCode,
			coordinates: point
		);

        log.info("Converting (" + municipalityPoint + ") into (" + location + ")");

        return location;
    }

}