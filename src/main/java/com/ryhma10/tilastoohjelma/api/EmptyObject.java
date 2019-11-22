package com.ryhma10.tilastoohjelma.api;

public class EmptyObject extends ApiData {
	
	String emptyObject;
	
	public EmptyObject(String emptyObject) {
		this.emptyObject = emptyObject;
	}

	public String getEmptyObject() {
		return emptyObject;
	}

	public void setEmptyObject(String emptyObject) {
		this.emptyObject = emptyObject;
	}
}
