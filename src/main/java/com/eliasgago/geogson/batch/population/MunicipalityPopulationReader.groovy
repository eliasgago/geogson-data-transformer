package com.eliasgago.geogson.batch.population

import java.util.List

import javax.annotation.PostConstruct

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

import com.eliasgago.geogson.domain.Location

@Component
class MunicipalityPopulationReader extends FlatFileItemReader<MunicipalityPopulation> {
	
	@PostConstruct
	final void setConfiguration() {
		
		this.setResource(new ClassPathResource("data/world/spain/galicia/galicia_poblacion_por_concellos.csv"));
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(";")
		delimitedLineTokenizer.setNames(["code", "name", "population"] as String[])
		
		BeanWrapperFieldSetMapper beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<MunicipalityPopulation>()
		beanWrapperFieldSetMapper.setTargetType(MunicipalityPopulation.class);
		
		DefaultLineMapper<MunicipalityPopulation> defaultLineMapper = new DefaultLineMapper<MunicipalityPopulation>()
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer)
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper)

		this.setLineMapper(defaultLineMapper);
	}
	
}
