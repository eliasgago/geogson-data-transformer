package com.eliasgago.geogson.batch.base;
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemWriter

import com.eliasgago.geogson.domain.Location

abstract class LocationWriter<T> implements ItemWriter<Location> {
	
	private StepExecution stepExecution;

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
	
	@Override
	public void write(List<? extends Location> items) throws Exception {
		ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		stepContext.put("finalListLocations", items);
	}
	
}