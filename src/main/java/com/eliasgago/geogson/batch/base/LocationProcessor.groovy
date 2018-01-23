package com.eliasgago.geogson.batch.base;
import java.util.List;

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemProcessor;

import com.eliasgago.geogson.domain.Location

abstract class LocationProcessor<T> implements ItemProcessor<T, Location> {

	List<Location> finalListLocations;

	@BeforeStep
	public void retrieveInterstepData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();
		this.finalListLocations = jobContext.get("finalListLocations");
	}
	
}