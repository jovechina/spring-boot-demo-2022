package com.jove.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomType {
	@Id
	private int roomId;
	private String roomType;

	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

}
