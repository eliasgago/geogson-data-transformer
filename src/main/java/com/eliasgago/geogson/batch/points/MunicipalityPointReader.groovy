package com.eliasgago.geogson.batch.points

import javax.annotation.PostConstruct

import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class MunicipalityPointReader extends FlatFileItemReader<MunicipalityPoint> {

	@PostConstruct
	final void setConfiguration() {
		
		this.setResource(new ClassPathResource("features/world/spain/galicia/municipality_points.csv"));
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer()
		delimitedLineTokenizer.setNames(["name", "province", "postalCode", "longitude", "latitude"] as String[])
		
		BeanWrapperFieldSetMapper beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<MunicipalityPoint>()
		beanWrapperFieldSetMapper.setTargetType(MunicipalityPoint.class);
		
		DefaultLineMapper<MunicipalityPoint> defaultLineMapper = new DefaultLineMapper<MunicipalityPoint>()
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer)
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper)

		this.setLineMapper(defaultLineMapper);
	}
	
}
