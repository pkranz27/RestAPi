package com.Pk.Udemy.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String fieldOne;
	
	private String fieldTwo;
	private String fieldThree;
	public SomeBean(String fieldOne, String fieldTwo, String fieldThree) {
		super();
		this.fieldOne = fieldOne;
		this.fieldTwo = fieldTwo;
		this.fieldThree = fieldThree;
	}
	public String getFieldOne() {
		return fieldOne;
	}
	public void setFieldOne(String fieldOne) {
		this.fieldOne = fieldOne;
	}
	public String getFieldTwo() {
		return fieldTwo;
	}
	public void setFieldTwo(String fieldTwo) {
		this.fieldTwo = fieldTwo;
	}
	public String getFieldThree() {
		return fieldThree;
	}
	public void setFieldThree(String fieldThree) {
		this.fieldThree = fieldThree;
	}
	@Override
	public String toString() {
		return "SomeBean [fieldOne=" + fieldOne + ", fieldTwo=" + fieldTwo + ", fieldThree=" + fieldThree + "]";
	}
}
