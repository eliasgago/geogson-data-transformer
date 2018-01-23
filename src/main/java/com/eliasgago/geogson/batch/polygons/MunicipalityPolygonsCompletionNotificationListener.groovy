package com.eliasgago.geogson.batch.polygons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.eliasgago.geogson.domain.Location

@Component
public class MunicipalityPolygonsCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(MunicipalityPolygonsCompletionNotificationListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			ExecutionContext jobContext = jobExecution.getExecutionContext();
			List<Location> locations = jobContext.get("finalListLocations");
			
			for(location in locations){
				log.info("Location -> " + location);
			}
	
		}
	}
}