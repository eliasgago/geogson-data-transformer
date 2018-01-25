package com.eliasgago.geogson.batch.base;
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired

import com.eliasgago.geogson.domain.Location
import com.eliasgago.geogson.domain.Locations

abstract class LocationProcessor<T> implements ItemProcessor<T, Location> {

	@Autowired
	Locations locations
	
	@BeforeStep
	public void retrieveInterstepData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();
		//this.finalListLocations = jobContext.get("finalListLocations");
	}
	
}