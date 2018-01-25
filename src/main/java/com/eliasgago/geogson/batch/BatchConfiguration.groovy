package com.eliasgago.geogson.batch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eliasgago.geogson.batch.points.MunicipalityPoint
import com.eliasgago.geogson.batch.points.MunicipalityPointProcessor
import com.eliasgago.geogson.batch.points.MunicipalityPointReader
import com.eliasgago.geogson.batch.points.MunicipalityPointWriter
import com.eliasgago.geogson.batch.points.MunicipalityPointsCompletionNotificationListener
import com.eliasgago.geogson.batch.polygons.MunicipalityPolygonProcessor
import com.eliasgago.geogson.batch.polygons.MunicipalityPolygonReader
import com.eliasgago.geogson.batch.polygons.MunicipalityPolygonWriter
import com.eliasgago.geogson.batch.population.MunicipalityPopulationProcessor
import com.eliasgago.geogson.batch.population.MunicipalityPopulationReader
import com.eliasgago.geogson.batch.population.MunicipalityPopulationWriter
import com.eliasgago.geogson.domain.Location
import com.eliasgago.geogson.domain.Locations

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

	
	@Autowired
	MunicipalityPointReader municipalityPointReader
	
	@Autowired
	MunicipalityPointProcessor municipalityPointProcessor
	
	@Autowired
	MunicipalityPointWriter municipalityPointWriter

	
	@Autowired
	MunicipalityPolygonReader municipalityPolygonReader
	
	@Autowired
	MunicipalityPolygonProcessor municipalityPolygonProcessor
	
	@Autowired
	MunicipalityPolygonWriter municipalityPolygonWriter

	
	@Autowired
	MunicipalityPopulationReader municipalityPopulationReader
	
	@Autowired
	MunicipalityPopulationProcessor municipalityPopulationProcessor
	
	@Autowired
	MunicipalityPopulationWriter municipalityPopulationWriter

	@Bean
	@JobScope
	public Locations locations() {
	  return new Locations();
	}
	
    @Bean
    public Job importUserJob(MunicipalityPointsCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(stepMunicipalityPolygon())
                .next(stepMunicipalityPoints())
                .next(stepMunicipalityPopulation())
                .build();
    }

	@Bean
	public ExecutionContextPromotionListener promotionListener() {
		ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(["finalListLocations"] as String[]);
		return listener;
	}
	
	@Bean
	public Step stepMunicipalityPolygon() {
		return stepBuilderFactory.get("stepMunicipalityPolygons")
				.<MunicipalityPoint, Location> chunk(1000)
				.listener(promotionListener())
				.reader(municipalityPolygonReader)
				.processor(municipalityPolygonProcessor)
				.writer(municipalityPolygonWriter)
				.build();
	}
	
	
    @Bean
    public Step stepMunicipalityPoints() {
        return stepBuilderFactory.get("stepMunicipalityPoints")
                .<MunicipalityPoint, Location> chunk(1000)
				.listener(promotionListener())
                .reader(municipalityPointReader)
                .processor(municipalityPointProcessor)
                .writer(municipalityPointWriter)
                .build();
    }
	
	@Bean
	public Step stepMunicipalityPopulation() {
		return stepBuilderFactory.get("stepMunicipalityPopulation")
				.<MunicipalityPoint, Location> chunk(1000)
				.listener(promotionListener())
				.reader(municipalityPopulationReader)
				.processor(municipalityPopulationProcessor)
				.writer(municipalityPopulationWriter)
				.build();
	}

}