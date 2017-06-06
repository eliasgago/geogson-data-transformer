package com.eliasgago.geogson.domain;

import java.util.Map;

import com.github.filosganga.geogson.model.Geometry;
import com.github.filosganga.geogson.model.Point;

public class Location {

	private String name;
	
	private String code;
	
	private String postalCode;
	
	private Point coordinates;

	private Geometry area;
	
	private Map<String, Object> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name.toLowerCase();
		if(name.contains(",")) {
			String[] nameSplitted = name.split(",");
			name = nameSplitted[1] + " " + nameSplitted[0];
		}
		if(name.contains("(")) {
			String[] nameSplitted = name.split(" \\(");
			name = nameSplitted[1].substring(0, nameSplitted[1].length() - 1) + " " + nameSplitted[0];
		}
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}

	public Geometry getArea() {
		return area;
	}

	public void setArea(Geometry area) {
		this.area = area;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Location){
			Location location = (Location) obj;
			if(location.getCode() != null && this.getCode() != null && location.getCode().equalsIgnoreCase(this.getCode())){
				return true;
			}
			if(location.getName() != null && this.getName() != null && location.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.name + " (code=" + this.code + ", postal_code=" + this.postalCode + ")";
	}
		
	
	
}
