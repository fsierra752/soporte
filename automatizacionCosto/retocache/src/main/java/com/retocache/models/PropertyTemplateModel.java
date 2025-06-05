package com.retocache.models;

public class PropertyTemplateModel {
	private String name;
	private String cardinality;
	private Integer length;
	private Integer dataType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "PropertyTemplateModel{" +
				"name='" + name + '\'' +
				", cardinality='" + cardinality + '\'' +
				", length=" + length +
				", dataType=" + dataType +
				'}';
	}
}
