package com.eliasgago.geogson.batch.points

import groovy.transform.ToString

@ToString(includes = ["name", "postalCode", "longitude", "latitude"])
class MunicipalityPoint {

	String name
	
	String province
	
	String postalCode
	
	Double longitude
	
	Double latitude
	
}
