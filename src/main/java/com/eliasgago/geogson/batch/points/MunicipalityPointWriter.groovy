package com.eliasgago.geogson.batch.points

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

import com.eliasgago.geogson.domain.Location

@Component
class MunicipalityPointWriter implements ItemWriter<Location> {
	
	private static final Logger log = LoggerFactory.getLogger(MunicipalityPointWriter.class);

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
