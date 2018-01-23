package com.eliasgago.geogson.batch.population

import groovy.transform.ToString

@ToString(includes = ["code", "name", "population", "latitude"])
class MunicipalityPopulation {
	
	String code
	
	String name
	
	String population
}
