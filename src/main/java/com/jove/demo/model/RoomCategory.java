package com.jove.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
// @Table(name="codemaster")
public class RoomCategory implements Serializable{
	
	private static final long serialVersionUID = -5567368518787829747L;
	
	@Id
	// @Column(name="key")
	private int categoryId;
	// @Column(name="value")
	private String categoryName;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "categoryid")
	private List<RoomService> roomServices;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<RoomService> getRoomServices() {
		return roomServices;
	}

	public void setRoomServices(List<RoomService> roomServices) {
		this.roomServices = roomServices;
	}
}
