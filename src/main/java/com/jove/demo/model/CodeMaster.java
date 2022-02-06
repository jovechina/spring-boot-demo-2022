package com.jove.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CodeMasterPKey.class)
public class CodeMaster implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int category;
	@Id
	private int key;
	private String Value;
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
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}

}
