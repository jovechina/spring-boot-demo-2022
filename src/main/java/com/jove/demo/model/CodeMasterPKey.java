package com.jove.demo.model;

import java.io.Serializable;

import javax.persistence.Id;

public class CodeMasterPKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int category;
	@Id
	private int key;
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
}
