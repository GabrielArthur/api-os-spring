package com.gabriel.os.controller.exceptions;

import java.io.Serializable;

public class FieldMessade implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String name;

	public FieldMessade() {
		super();
	}

	public FieldMessade(String fieldName, String name) {
		super();
		this.fieldName = fieldName;
		this.name = name;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
